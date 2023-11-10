package id.allana.shoppan.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.allana.shoppan.R
import id.allana.shoppan.databinding.FragmentDetailProfileBinding

class DetailProfileFragment : Fragment() {

    private var _binding: FragmentDetailProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateBack()
        hideBottomNavigation()
    }

    private fun navigateBack() {
        binding.toolbarDetailProfile.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        bottomNav?.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        bottomNav?.visibility = View.GONE
    }
}