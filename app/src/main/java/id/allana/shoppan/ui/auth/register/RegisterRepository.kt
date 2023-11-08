package id.allana.shoppan.ui.auth.register

import id.allana.shoppan.network.datasource.auth.AuthDataSource
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import java.io.File

class RegisterRepository(
    private val authDataSource: AuthDataSource
): RegisterContract.Repository {

    override suspend fun postRegisterUser(registerRequestBody: RegisterRequest): BaseNullDataResponse {
        val registerResponse = authDataSource.postRegisterUser(registerRequestBody)
        if (registerResponse.status) {
            registerResponse.message
        } else {
            registerResponse.error
        }
        return registerResponse
    }

    override suspend fun postUploadPhotoUser(image: File): BaseNullDataResponse {
        val uploadPhotoResponse = authDataSource.postUploadPhotoUser(image)
        if (uploadPhotoResponse.status) {
            uploadPhotoResponse.message
        } else {
            uploadPhotoResponse.error
        }
        return uploadPhotoResponse
    }
}