package id.allana.shoppan.network.service

import id.allana.shoppan.network.response.BaseResponse
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.data.auth.UserTokenResponse
import id.allana.shoppan.network.response.BaseNullDataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {

    @POST("user/login")
    suspend fun postLoginUser(@Body loginRequestBody: LoginRequest): BaseResponse<UserTokenResponse>

    @POST("user/register")
    suspend fun postRegisterUser(@Body registerRequestBody: RegisterRequest): BaseNullDataResponse

    @GET("user/profile")
    suspend fun getProfileUser()
}
