package id.allana.shoppan.network.datasource.auth

import id.allana.shoppan.network.api.ApiConfig
import id.allana.shoppan.network.response.BaseResponse
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import id.allana.shoppan.network.response.BaseNullDataResponse

class AuthDataSourceImpl: AuthDataSource {
    override suspend fun postLoginUser(loginRequestBody: LoginRequest): BaseResponse<UserTokenResponse> {
        return ApiConfig.getApiService().postLoginUser(loginRequestBody)
    }

    override suspend fun postRegisterUser(registerRequestBody: RegisterRequest): BaseNullDataResponse {
        return ApiConfig.getApiService().postRegisterUser(registerRequestBody)
    }
}