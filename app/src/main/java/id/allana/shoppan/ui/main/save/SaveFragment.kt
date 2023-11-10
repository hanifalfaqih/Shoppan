package id.allana.shoppan.ui.main.save

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.allana.shoppan.databinding.FragmentSaveBinding

class SaveFragment : Fragment() {

    private var _binding: FragmentSaveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

}