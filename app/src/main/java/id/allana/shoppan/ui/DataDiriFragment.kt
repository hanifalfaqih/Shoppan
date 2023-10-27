package id.allana.shoppan.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.allana.shoppan.R
import id.allana.shoppan.databinding.FragmentDataDiriBinding

class DataDiriFragment : Fragment() {

    private var _binding: FragmentDataDiriBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataDiriBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}