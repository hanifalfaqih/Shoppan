package id.allana.shoppan.ui.auth.datadiri

import android.app.Activity
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.dhaval2404.imagepicker.ImagePicker
import id.allana.shoppan.R
import id.allana.shoppan.base.BaseFragment
import id.allana.shoppan.base.GenericViewModelFactory
import id.allana.shoppan.base.Resource
import id.allana.shoppan.databinding.FragmentDataDiriBinding
import id.allana.shoppan.network.datasource.auth.AuthDataSourceImpl
import id.allana.shoppan.network.request.RegisterRequest
import id.allana.shoppan.ui.auth.register.RegisterRepository
import id.allana.shoppan.ui.auth.register.RegisterViewModel
import java.io.File

class DataDiriFragment : BaseFragment<FragmentDataDiriBinding, RegisterViewModel>(
    FragmentDataDiriBinding::inflate
), DataDiriContract.View {

    private val args by navArgs<DataDiriFragmentArgs>()

    private var selectedPictures: File? = null

    private val startForImageUserResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data
                    getViewBinding().ivPhotoUser.setImageURI(fileUri)

                    fileUri?.path?.let {
                        val file = File(it)
                        if (file.exists()) {
                            selectedPictures = file
                        }
                    }

                    selectedPictures?.let { getViewModel().uploadPhotoUser(it) }
                }
            }
        }
    override fun initView() {
        val email = args.emailUser
        val password = args.passwordUser
        val phoneNumber = args.phoneNumberUser
        setOnClick(email, password, phoneNumber)
    }

    override fun initViewModel(): RegisterViewModel {
        val authDataSource = AuthDataSourceImpl()
        val repository = RegisterRepository(authDataSource)
        return GenericViewModelFactory(RegisterViewModel(repository)).create(RegisterViewModel::class.java)
    }

    override fun setOnClick(email: String, password: String, phoneNumber: String) {
        getViewBinding().btnSimpan.setOnClickListener {
            if (checkFormValidation()) {
                getViewModel().registerUser(
                    RegisterRequest(
                        email = email,
                        password = password,
                        phone = phoneNumber,
                        name = getViewBinding().etName.text.toString().trim(),
                        school = getViewBinding().etSchool.text.toString().trim(),
                        address = getViewBinding().etAddress.text.toString().trim()
                    )
                )
            }
        }

        getViewBinding().ivPhotoUser.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .maxResultSize(1080, 1080)
                .createIntent { intent ->
                    startForImageUserResult.launch(intent)
                }
        }
    }

    override fun checkFormValidation(): Boolean {
        val isFormValid: Boolean
        val name = getViewBinding().etName.text.toString().trim()
        val school = getViewBinding().etSchool.text.toString().trim()
        val address = getViewBinding().etAddress.text.toString().trim()

        if (name.isEmpty()) {
            isFormValid = false
            getViewBinding().etName.error = "Nama tidak boleh kosong"
        } else if (school.isEmpty()) {
            isFormValid = false
            getViewBinding().etSchool.error = "Sekolah tidak boleh kosong"
        } else if (address.isEmpty()) {
            isFormValid = false
            getViewBinding().etAddress.error = "Alamat tidak boleh kosong"
        } else {
            isFormValid = true
        }
        return isFormValid
    }

    override fun observeData() {
        getViewModel().registerResultLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                }
                is Resource.Success -> {
                    navigateToLogin()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, response.message)
                }
            }
        }

        getViewModel().uploadPhotoUserLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false)
                }
                is Resource.Success -> {
                    Toast.makeText(context, "Upload Photo User -> BERHASIL", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, response.message)
                }
            }
        }
    }

    override fun showLoading(isLoading: Boolean) {
        getViewBinding().pbRegister.isVisible = isLoading
    }

    override fun showError(isError: Boolean, msg: String?) {
        if (isError) Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToLogin() {
        findNavController().navigate(R.id.action_dataDiriFragment_to_loginFragment)
    }
}