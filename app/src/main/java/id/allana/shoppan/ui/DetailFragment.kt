package id.allana.shoppan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import id.allana.shoppan.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameProduct = args.nameProduct
        val priceProduct = args.priceProduct
        val sellerName = args.sellerName
        val descriptionProduct = args.descriptionProduct
        setDataToDetail(nameProduct, priceProduct, sellerName, descriptionProduct)
    }

    private fun setDataToDetail(nameProduct: String, priceProduct: Int, sellerName: String, descriptionProduct: String) {
        binding.tvProductName.text = nameProduct
        binding.tvProductPrice.text = priceProduct.toString()
        binding.tvNamaPenjual.text = sellerName
        binding.tvIsiDeskripsi.text = descriptionProduct
    }
}