package id.allana.shoppan.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import id.allana.shoppan.network.datasource.preference.PreferenceDataSourceImpl
import id.allana.shoppan.network.datasource.product.ProductDataSource
import id.allana.shoppan.network.response.ResponseListProduct

class HomeRepository(
    private val productDataSource: ProductDataSource,
    private val preferenceDataSourceImpl: PreferenceDataSourceImpl
): HomeContract.Repository {
    override suspend fun getAllSellProduct(authToken: String): ResponseListProduct {
        val getAllSellProduct = productDataSource.getAllSellProduct(authToken)
        if (getAllSellProduct.status) {
            getAllSellProduct.data
        } else {
            getAllSellProduct.error
        }
        return getAllSellProduct
    }

    override fun getAuthToken(): LiveData<String> {
        return preferenceDataSourceImpl.getToken().asLiveData()
    }
}