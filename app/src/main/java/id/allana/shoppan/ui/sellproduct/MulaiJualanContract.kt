package id.allana.shoppan.ui.sellproduct

import androidx.lifecycle.LiveData
import id.allana.shoppan.base.BaseContract
import id.allana.shoppan.network.request.SellProductRequest
import id.allana.shoppan.network.response.BaseNullDataResponse

interface MulaiJualanContract {
    interface View : BaseContract.BaseView {
        fun setOnClick()
        fun checkFormValidation(): Boolean
        fun navigateToProfile()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun sellProduct(authToken: String,productRequestBody: SellProductRequest)
        fun getAuthToken(): LiveData<String>
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun postRegisterUser(
            authToken: String,
            productRequestBody: SellProductRequest
        ): BaseNullDataResponse

        fun getAuthToken(): LiveData<String>
    }
}