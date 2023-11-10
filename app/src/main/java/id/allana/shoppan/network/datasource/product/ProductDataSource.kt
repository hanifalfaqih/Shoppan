package id.allana.shoppan.network.datasource.product

import id.allana.shoppan.network.request.SellProductRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import id.allana.shoppan.network.response.ResponseListProduct

interface ProductDataSource {

    suspend fun postSellProduct(authToken: String, sellProductRequestBody: SellProductRequest): BaseNullDataResponse
    suspend fun getAllSellProduct(authToken: String): ResponseListProduct


}