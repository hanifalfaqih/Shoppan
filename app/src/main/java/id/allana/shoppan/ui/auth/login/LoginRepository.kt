package id.allana.shoppan.ui.auth.login

import id.allana.shoppan.network.response.BaseResponse
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import id.allana.shoppan.network.datasource.auth.AuthDataSource
import id.allana.shoppan.network.datasource.preference.PreferenceDataSource

class LoginRepository(
    private val authDataSource: AuthDataSource,
    private val preferenceDataSource: PreferenceDataSource
): LoginContract.Repository {

    override suspend fun postLoginUser(loginRequestBody: LoginRequest): BaseResponse<UserTokenResponse> {
        val loginResponse = authDataSource.postLoginUser(loginRequestBody)
        if (loginResponse.status) {
            loginResponse.data?.token?.let { preferenceDataSource.setToken(it) }
        } else {
            loginResponse.error
        }
        return loginResponse
    }

}