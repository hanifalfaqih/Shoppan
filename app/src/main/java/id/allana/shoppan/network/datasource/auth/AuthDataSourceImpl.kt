package id.allana.shoppan.network.datasource.auth

import id.allana.shoppan.network.api.ApiConfig
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import id.allana.shoppan.network.response.BaseResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AuthDataSourceImpl: AuthDataSource {
    override suspend fun postLoginUser(loginRequestBody: LoginRequest): BaseResponse<UserTokenResponse> {
        return ApiConfig.getAuthApiService().postLoginUser(loginRequestBody)
    }

    override suspend fun postRegisterUser(registerRequestBody: RegisterRequest): BaseNullDataResponse {
        return ApiConfig.getAuthApiService().postRegisterUser(registerRequestBody)
    }

    override suspend fun postUploadPhotoUser(image: File): BaseNullDataResponse {
        val requestBody = image.asRequestBody("image/*".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData(
            "photo",
            image.name,
            requestBody
        )
        return ApiConfig.getAuthApiService().postUploadPhotoUser(filePart)
    }
}