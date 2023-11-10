package id.allana.shoppan.ui.sellproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.allana.shoppan.base.BaseViewModelImpl
import id.allana.shoppan.base.Resource
import id.allana.shoppan.network.request.SellProductRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MulaiJualanViewModel(
    private val mulaiJualanRepository: MulaiJualanRepository

): BaseViewModelImpl(), MulaiJualanContract.ViewModel {

    private val _sellProductResultLiveData = MutableLiveData<Resource<BaseNullDataResponse>>()
    val sellProductResultLiveData: LiveData<Resource<BaseNullDataResponse>> = _sellProductResultLiveData
    override fun sellProduct(authToken: String, productRequestBody: SellProductRequest) {
        _sellProductResultLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mulaiJualanRepository.postRegisterUser(authToken, productRequestBody)

                if (response.status) {
                    viewModelScope.launch(Dispatchers.Main) {
                        _sellProductResultLiveData.value = Resource.Success(response)
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        _sellProductResultLiveData.value = Resource.Error(response.message)
                    }
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    _sellProductResultLiveData.value = e.localizedMessage?.let { Resource.Error(it) }
                }
            }
        }
    }

    override fun getAuthToken(): LiveData<String> {
        return mulaiJualanRepository.getAuthToken()
    }
}