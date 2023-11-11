package id.allana.shoppan.ui.sellproduct

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.allana.shoppan.R
import id.allana.shoppan.base.BaseFragment
import id.allana.shoppan.base.GenericViewModelFactory
import id.allana.shoppan.base.Resource
import id.allana.shoppan.databinding.FragmentMulaiJualanBinding
import id.allana.shoppan.network.data.datastore.UserPreferences
import id.allana.shoppan.network.datasource.preference.PreferenceDataSourceImpl
import id.allana.shoppan.network.datasource.product.ProductDataSourceImpl
import id.allana.shoppan.network.request.SellProductRequest

class MulaiJualanFragment : BaseFragment<FragmentMulaiJualanBinding, MulaiJualanViewModel>(
    FragmentMulaiJualanBinding::inflate
), MulaiJualanContract.View {

    private val Context.dataStore by preferencesDataStore("setting")
    private lateinit var authToken: String

    override fun initView() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        bottomNav?.visibility = View.GONE

        getViewBinding().toolbarMulaiJualan.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        setOnClick()
    }

    override fun initViewModel(): MulaiJualanViewModel {
        val productDataSource = ProductDataSourceImpl()
        val userPreferences = UserPreferences.getInstance(requireContext().dataStore)
        val preferenceDataSource = PreferenceDataSourceImpl(userPreferences)
        val repository = MulaiJualanRepository(productDataSource, preferenceDataSource)
        return GenericViewModelFactory(MulaiJualanViewModel(repository)).create(MulaiJualanViewModel::class.java)
    }

    override fun setOnClick() {
        getViewBinding().apply {
            btnSimpan.setOnClickListener {
                if (checkFormValidation()) {
                    getViewModel().sellProduct(authToken,
                        SellProductRequest(
                            name = getViewBinding().etNameProduct.text.toString().trim(),
                            category = getViewBinding().etCategoryProduct.text.toString().trim(),
                            description = getViewBinding().etDescriptionProduct.text.toString().trim(),
                            price = getViewBinding().etPriceProduct.text.toString().toInt(),
                            slogan = "slogan",
                            photo = "on progress"
                        )
                    )
                }
            }
        }
    }

    override fun checkFormValidation(): Boolean {
        val isFormValid: Boolean
        val nameProduct = getViewBinding().etNameProduct.text.toString().trim()
        val categoryProduct = getViewBinding().etCategoryProduct.text.toString().trim()
        val descriptionProduct = getViewBinding().etDescriptionProduct.text.toString().trim()
        val priceProduct = getViewBinding().etPriceProduct.text.toString().trim()

        if (nameProduct.isEmpty()) {
            isFormValid = false
            getViewBinding().etNameProduct.error = "Nama produk tidak boleh kosong"
        } else if (categoryProduct.isEmpty()) {
            isFormValid = false
            getViewBinding().etCategoryProduct.error = "Kategori produk tidak boleh kosong"
        } else if (descriptionProduct.isEmpty()) {
            isFormValid = false
            getViewBinding().etDescriptionProduct.error = "Deskripsi produk tidak boleh kosong"
        } else if (priceProduct.isEmpty()) {
            isFormValid = false
            getViewBinding().etPriceProduct.error = "Harga produk tidak boleh kosong"
        } else {
            isFormValid = true
        }
        return isFormValid
    }

    override fun navigateToProfile() {
        findNavController().popBackStack()
        Toast.makeText(requireContext(), "Berhasil tambah produk", Toast.LENGTH_SHORT).show()
    }

    override fun observeData() {
        getViewModel().getAuthToken().observe(viewLifecycleOwner) { response ->
            authToken = response
        }
        getViewModel().sellProductResultLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                }
                is Resource.Success -> {
                    navigateToProfile()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, response.message)
                }
            }
        }
    }

    override fun showLoading(isLoading: Boolean) {
        getViewBinding().pbSellProduct.isVisible = isLoading
    }

    override fun showError(isError: Boolean, msg: String?) {
        if (isError) Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        bottomNav?.visibility = View.VISIBLE
    }
}