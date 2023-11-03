package id.allana.shoppan.ui.auth.register

import id.allana.shoppan.network.datasource.auth.AuthDataSource
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.response.BaseNullDataResponse

class RegisterRepository(
    private val authDataSource: AuthDataSource
) {
    suspend fun postRegisterUser(registerRequestBody: RegisterRequest): BaseNullDataResponse {
        return authDataSource.postRegisterUser(registerRequestBody)
    }
}