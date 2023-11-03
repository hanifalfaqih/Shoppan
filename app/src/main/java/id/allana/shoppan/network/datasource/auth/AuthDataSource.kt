package id.allana.shoppan.network.datasource.auth

import id.allana.shoppan.network.response.BaseResponse
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import id.allana.shoppan.network.response.BaseNullDataResponse

interface AuthDataSource {
    suspend fun postLoginUser(loginRequestBody: LoginRequest): BaseResponse<UserTokenResponse>
    suspend fun postRegisterUser(registerRequestBody: RegisterRequest): BaseNullDataResponse

}