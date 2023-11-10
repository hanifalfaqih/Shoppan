package id.allana.shoppan.network.service

import id.allana.shoppan.network.request.SellProductRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import id.allana.shoppan.network.response.ResponseListProduct
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApiService {

    @POST("/product/create")
    suspend fun postSellProduct(@Body sellProductRequestBody: SellProductRequest): BaseNullDataResponse

    @GET("/product/all")
    suspend fun getAllSellProduct(): ResponseListProduct
}