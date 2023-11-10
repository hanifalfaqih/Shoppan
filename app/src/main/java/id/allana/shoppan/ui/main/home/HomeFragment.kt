package id.allana.shoppan.ui.main.home

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.GridLayoutManager
import id.allana.shoppan.base.BaseFragment
import id.allana.shoppan.base.GenericViewModelFactory
import id.allana.shoppan.base.Resource
import id.allana.shoppan.databinding.FragmentHomeBinding
import id.allana.shoppan.network.data.datastore.UserPreferences
import id.allana.shoppan.network.datasource.preference.PreferenceDataSourceImpl
import id.allana.shoppan.network.datasource.product.ProductDataSourceImpl
import id.allana.shoppan.network.response.DataItem


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
), HomeContract.View {

    private val Context.dataStore by preferencesDataStore("setting")

    private lateinit var homeAdapter: HomeAdapter
    override fun initView() {
        initList()
    }

    override fun initViewModel(): HomeViewModel {
        val productDataSource = ProductDataSourceImpl()
        val userPreferences = UserPreferences.getInstance(requireContext().dataStore)
        val preferenceDataSource = PreferenceDataSourceImpl(userPreferences)
        val repository = HomeRepository(productDataSource, preferenceDataSource)
        return GenericViewModelFactory(HomeViewModel(repository)).create(HomeViewModel::class.java)
    }

    override fun initList() {
        homeAdapter = HomeAdapter()
        getViewBinding().rvRecommendationProductAndFood.apply {
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun setDataAdapter(listItem: List<DataItem>) {
        this.homeAdapter.setListItems(listItem)
        getViewBinding().rvRecommendationProductAndFood.adapter = this.homeAdapter
    }

    override fun observeData() {
        getViewModel().getAuthToken().observe(viewLifecycleOwner) { response ->
            getViewModel().getAllSellProduct(response)
        }

        getViewModel().getAllSellProductResultLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    response.data?.let { setDataAdapter(it) }
                    showError(false)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, response.message)
                }
            }
        }
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvRecommendationProductAndFood.isVisible = isVisible
    }

    override fun showLoading(isLoading: Boolean) {
        getViewBinding().pbData.isVisible = isLoading
    }

    override fun showError(isError: Boolean, msg: String?) {
        if (isError) Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}