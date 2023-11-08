package id.allana.shoppan.ui.auth.register

import id.allana.shoppan.base.BaseContract
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.network.response.BaseNullDataResponse
import java.io.File

interface RegisterContract {
    interface View: BaseContract.BaseView {
        fun setOnClick()
        fun checkFormValidation(): Boolean
        fun navigateToDataDiri()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun registerUser(registerRequestBody: RegisterRequest)
        fun uploadPhotoUser(image: File)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun postRegisterUser(registerRequestBody: RegisterRequest): BaseNullDataResponse
        suspend fun postUploadPhotoUser(image: File): BaseNullDataResponse
    }
}