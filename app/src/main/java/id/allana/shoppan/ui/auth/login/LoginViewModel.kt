package id.allana.shoppan.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.allana.shoppan.base.BaseViewModelImpl
import id.allana.shoppan.base.Resource
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
): BaseViewModelImpl(), LoginContract.ViewModel {

    private val _loginResultLiveData = MutableLiveData<Resource<UserTokenResponse?>>()
    val loginResultLiveData: LiveData<Resource<UserTokenResponse?>> = _loginResultLiveData

    override fun loginUser(loginRequestBody: LoginRequest) {
        _loginResultLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = loginRepository.postLoginUser(loginRequestBody)

                if (response.status) {
                    viewModelScope.launch(Dispatchers.Main) {
                        _loginResultLiveData.value = Resource.Success(response.data)
                    }
                } else {
                    viewModelScope.launch(Dispatchers.Main) {
                        _loginResultLiveData.value = Resource.Error(response.message)
                    }
                }

            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    _loginResultLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }
}