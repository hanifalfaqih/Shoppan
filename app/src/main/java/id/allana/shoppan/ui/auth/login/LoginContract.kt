package id.allana.shoppan.ui.auth.login

import id.allana.shoppan.base.BaseContract
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import id.allana.shoppan.network.response.BaseResponse

interface LoginContract {
    interface View: BaseContract.BaseView {
        fun setOnClick()
        fun checkFormValidation(): Boolean
        fun navigateToHome()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun loginUser(loginRequestBody: LoginRequest)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun postLoginUser(loginRequestBody: LoginRequest): BaseResponse<UserTokenResponse>
    }
}