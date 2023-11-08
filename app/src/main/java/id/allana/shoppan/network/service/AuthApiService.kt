package id.allana.shoppan.network.service

import id.allana.shoppan.network.response.BaseResponse
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import id.allana.shoppan.network.response.BaseNullDataResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthApiService {

    @POST("user/login")
    suspend fun postLoginUser(@Body loginRequestBody: LoginRequest): BaseResponse<UserTokenResponse>

    @POST("user/register")
    suspend fun postRegisterUser(@Body registerRequestBody: RegisterRequest): BaseNullDataResponse

    @Multipart
    @POST("user/file/upload")
    suspend fun postUploadPhotoUser(@Part image: MultipartBody.Part): BaseNullDataResponse

    @GET("user/profile")
    suspend fun getProfileUser()
}
