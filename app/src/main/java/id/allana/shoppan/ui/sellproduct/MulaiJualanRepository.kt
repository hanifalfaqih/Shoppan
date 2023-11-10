package id.allana.shoppan.ui.sellproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import id.allana.shoppan.network.datasource.preference.PreferenceDataSourceImpl
import id.allana.shoppan.network.datasource.product.ProductDataSource
import id.allana.shoppan.network.request.SellProductRequest
import id.allana.shoppan.network.response.BaseNullDataResponse

class MulaiJualanRepository(
    private val productDataSource: ProductDataSource,
    private val preferenceDataSourceImpl: PreferenceDataSourceImpl
): MulaiJualanContract.Repository {
    override suspend fun postRegisterUser(authToken: String, productRequestBody: SellProductRequest): BaseNullDataResponse {
        val sellProductResponse = productDataSource.postSellProduct(authToken, productRequestBody)
        if (sellProductResponse.status) {
            sellProductResponse.message
        } else {
            sellProductResponse.error
        }
        return sellProductResponse
    }

    override fun getAuthToken(): LiveData<String> {
        return preferenceDataSourceImpl.getToken().asLiveData()
    }
}