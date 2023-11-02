package id.allana.shoppan.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.allana.shoppan.base.Resource
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerRepository: RegisterRepository
): ViewModel() {

    private val _registerResultLiveData = MutableLiveData<Resource<BaseNullDataResponse>>()
    val registerResultLiveData: LiveData<Resource<BaseNullDataResponse>> = _registerResultLiveData

    fun registerUser(registerRequestBody: RegisterRequest) {
        _registerResultLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = registerRepository.postRegisterUser(registerRequestBody)

                if (response.status) {
                    viewModelScope.launch(Dispatchers.Main) {
                        _registerResultLiveData.value = Resource.Success(response)
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        _registerResultLiveData.value = Resource.Error(response.message)
                    }
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    _registerResultLiveData.value = e.localizedMessage?.let { Resource.Error(it) }
                }
            }
        }
    }
}