package id.allana.shoppan.ui.main.home

import androidx.lifecycle.LiveData
import id.allana.shoppan.base.BaseContract
import id.allana.shoppan.network.response.DataItem
import id.allana.shoppan.network.response.ResponseListProduct

interface HomeContract {

    interface View : BaseContract.BaseView {
        fun initList()
        fun setDataAdapter(listItem: List<DataItem>)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getAllSellProduct(authToken: String)
        fun getAuthToken(): LiveData<String>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllSellProduct(authToken: String): ResponseListProduct
        fun getAuthToken(): LiveData<String>
    }
}