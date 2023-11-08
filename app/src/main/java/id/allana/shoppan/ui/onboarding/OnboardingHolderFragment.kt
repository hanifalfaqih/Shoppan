package id.allana.shoppan.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.allana.shoppan.databinding.FragmentOnboardingHolderBinding


class OnboardingHolderFragment(
    private val title: String,
    private val description: String,
) : Fragment() {

    private var _binding: FragmentOnboardingHolderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingHolderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvTitleOnboarding.text = title
            tvDescriptionOnboarding.text = description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}