package id.allana.shoppan.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.allana.shoppan.R
import id.allana.shoppan.databinding.FragmentDetailBinding
import java.net.URLEncoder

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

        hideBottomNavigation()

        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnBuyNow.setOnClickListener {
            val phoneNumber = 6282221365052
            val message = "Halo saya mau beli $nameProduct\nMetode pembayaran: COD / Bayar di rumah\nJumlah produk: "
            val url = "https://api.whatsapp.com/send?phone=$phoneNumber"+"&text=" + URLEncoder.encode(message, "UTF-8")

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
        
        binding.btnSave.setOnClickListener {
            Toast.makeText(context, "Simpan barang!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDataToDetail(nameProduct: String, priceProduct: Int, sellerName: String, descriptionProduct: String) {
        binding.tvProductName.text = nameProduct
        binding.tvProductPrice.text = priceProduct.toString()
        binding.tvNamaPenjual.text = sellerName
        binding.tvIsiDeskripsi.text = descriptionProduct
    }

    private fun hideBottomNavigation() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        bottomNav?.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_nav_main)
        bottomNav?.visibility = View.VISIBLE
    }
}
