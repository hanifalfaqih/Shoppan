package id.allana.shoppan.base

interface BaseContract {

    interface BaseView {
        fun observeData()
        fun showContent(isVisible: Boolean)
        fun showLoading(isLoading: Boolean)
        fun showError(isError: Boolean, msg: String? = null)
    }

    interface BaseViewModel
    interface BaseRepository
}