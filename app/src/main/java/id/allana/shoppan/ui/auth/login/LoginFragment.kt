package id.allana.shoppan.ui.auth.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.fragment.findNavController
import id.allana.shoppan.R
import id.allana.shoppan.base.BaseFragment
import id.allana.shoppan.base.GenericViewModelFactory
import id.allana.shoppan.base.Resource
import id.allana.shoppan.databinding.FragmentLoginBinding
import id.allana.shoppan.network.data.auth.LoginRequest
import id.allana.shoppan.network.data.datastore.UserPreferences
import id.allana.shoppan.network.datasource.auth.AuthDataSourceImpl
import id.allana.shoppan.network.datasource.preference.PreferenceDataSourceImpl
import id.allana.shoppan.ui.main.MainActivity
import id.allana.shoppan.util.StringUtils


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    FragmentLoginBinding::inflate
), LoginContract.View {

    private val Context.dataStore by preferencesDataStore("setting")

    override fun initView() {
        setOnClick()
    }

    override fun initViewModel(): LoginViewModel {
        val userPreferences = UserPreferences.getInstance(requireContext().dataStore)
        val authDataSource = AuthDataSourceImpl()
        val preferenceDataSource = PreferenceDataSourceImpl(userPreferences)
        val repository = LoginRepository(authDataSource, preferenceDataSource)
        return GenericViewModelFactory(LoginViewModel(repository)).create(LoginViewModel::class.java)
    }

    override fun setOnClick() {
        getViewBinding().apply {
            btnSignIn.setOnClickListener {
                if (checkFormValidation()) {
                    getViewModel().loginUser(
                        LoginRequest(
                            email = getViewBinding().etEmail.text.toString().trim(),
                            password = getViewBinding().etPassword.text.toString().trim()
                        )
                    )
                }
            }

            tvDontHaveAccountSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    override fun checkFormValidation(): Boolean {
        val isFormValid: Boolean
        val email = getViewBinding().etEmail.text.toString().trim()
        val password = getViewBinding().etPassword.text.toString().trim()

        if (email.isEmpty()) {
            isFormValid = false
            getViewBinding().etEmail.error = "Email tidak boleh kosong"
        } else if (!StringUtils.isEmailValid(email)) {
            isFormValid = false
            getViewBinding().etEmail.error = "Email tidak valid"
        } else if (password.isEmpty()) {
            isFormValid = false
            getViewBinding().etPassword.error = "Password tidak boleh kosong"
        } else {
            isFormValid = true
        }
        return isFormValid
    }

    override fun observeData() {
        getViewModel().loginResultLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                }

                is Resource.Success -> {
                    navigateToHome()
                }

                is Resource.Error -> {
                    showLoading(false)
                    showError(true, response.message)
                }
            }
        }
    }

    override fun showLoading(isLoading: Boolean) {
        getViewBinding().pbLogin.isVisible = isLoading
    }

    override fun showError(isError: Boolean, msg: String?) {
        if (isError) Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToHome() {
        val navigate = Intent(requireContext(), MainActivity::class.java)
        startActivity(navigate)
    }
}