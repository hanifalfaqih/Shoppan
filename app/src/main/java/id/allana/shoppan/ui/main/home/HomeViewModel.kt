package id.allana.shoppan.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.allana.shoppan.base.BaseViewModelImpl
import id.allana.shoppan.base.Resource
import id.allana.shoppan.network.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
): BaseViewModelImpl(), HomeContract.ViewModel {

    private val _getAllSellProductResultLiveData = MutableLiveData<Resource<List<DataItem>>>()
    val getAllSellProductResultLiveData: LiveData<Resource<List<DataItem>>> = _getAllSellProductResultLiveData
    override fun getAllSellProduct(authToken: String) {
        _getAllSellProductResultLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = homeRepository.getAllSellProduct(authToken)

                if (response.status) {
                    viewModelScope.launch(Dispatchers.Main) {
                        _getAllSellProductResultLiveData.value = Resource.Success(response.data)
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        _getAllSellProductResultLiveData.value = Resource.Error(response.message)
                    }
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    _getAllSellProductResultLiveData.value = e.localizedMessage?.let { Resource.Error(it) }
                }
            }
        }
    }

    override fun getAuthToken(): LiveData<String> {
        return homeRepository.getAuthToken()
    }
}