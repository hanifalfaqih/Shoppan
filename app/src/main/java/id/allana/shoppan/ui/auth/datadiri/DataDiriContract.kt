package id.allana.shoppan.ui.auth.datadiri

import id.allana.shoppan.base.BaseContract

interface DataDiriContract {
    interface View: BaseContract.BaseView {
        fun setOnClick(email: String, password: String, phoneNumber: String)
        fun checkFormValidation(): Boolean
        fun navigateToLogin()
    }
}