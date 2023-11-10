package id.allana.shoppan.network.datasource.product

import id.allana.shoppan.network.api.ApiConfig
import id.allana.shoppan.network.request.SellProductRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import id.allana.shoppan.network.response.ResponseListProduct

class ProductDataSourceImpl: ProductDataSource {
    override suspend fun postSellProduct(authToken: String, sellProductRequestBody: SellProductRequest): BaseNullDataResponse {
        return ApiConfig.getProductApiService(authToken).postSellProduct(sellProductRequestBody)
    }

    override suspend fun getAllSellProduct(authToken: String): ResponseListProduct {
        return ApiConfig.getProductApiService(authToken).getAllSellProduct()
    }
}