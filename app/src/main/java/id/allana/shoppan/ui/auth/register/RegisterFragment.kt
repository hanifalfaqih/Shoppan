package id.allana.shoppan.ui.auth.register

import androidx.navigation.fragment.findNavController
import id.allana.shoppan.base.BaseFragment
import id.allana.shoppan.base.GenericViewModelFactory
import id.allana.shoppan.databinding.FragmentRegisterBinding
import id.allana.shoppan.network.datasource.auth.AuthDataSourceImpl
import id.allana.shoppan.util.StringUtils


class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(
    FragmentRegisterBinding::inflate
), RegisterContract.View {
    override fun initView() {
        setOnClick()
    }

    override fun initViewModel(): RegisterViewModel {
        val authDataSource = AuthDataSourceImpl()
        val repository = RegisterRepository(authDataSource)
        return GenericViewModelFactory(RegisterViewModel(repository)).create(RegisterViewModel::class.java)
    }

    override fun setOnClick() {
        getViewBinding().apply {
            btnSignUp.setOnClickListener {
                if (checkFormValidation()) {
                    navigateToDataDiri()
                }
            }
        }
    }

    override fun checkFormValidation(): Boolean {
        val isFormValid: Boolean
        val email = getViewBinding().etEmail.text.toString().trim()
        val password = getViewBinding().etPassword.text.toString().trim()
        val phoneNumber = getViewBinding().etPhoneNumber.text.toString().trim()

        if (email.isEmpty()) {
            isFormValid = false
            getViewBinding().etEmail.error = "Email tidak boleh kosong"
        } else if(!StringUtils.isEmailValid(email)) {
            isFormValid = false
            getViewBinding().etEmail.error = "Email tidak valid"
        } else if (password.isEmpty()) {
            isFormValid = false
            getViewBinding().etPassword.error = "Password tidak boleh kosong"
        } else if (phoneNumber.isEmpty()) {
            isFormValid = false
            getViewBinding().etPhoneNumber.error = "Nomor HP tidak boleh kosong"
        } else {
            isFormValid = true
        }
        return isFormValid
    }

    override fun navigateToDataDiri() {
        val navigateToDataDiri = RegisterFragmentDirections.actionRegisterFragmentToDataDiriFragment(
            getViewBinding().etEmail.text.toString().trim(),
            getViewBinding().etPassword.text.toString().trim(),
            getViewBinding().etPhoneNumber.text.toString().trim()
        )
        findNavController().navigate(navigateToDataDiri)
    }

}