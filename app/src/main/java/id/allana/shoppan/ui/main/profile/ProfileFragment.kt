package id.allana.shoppan.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.allana.shoppan.R
import id.allana.shoppan.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToDetailProfile()
        navigateToMulaiJualan()
    }

    private fun navigateToDetailProfile() {
        binding.btnLihatProfil.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_detailProfileFragment)
        }
    }

    private fun navigateToMulaiJualan() {
        binding.btnMulaiJualan.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_mulaiJualanFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}