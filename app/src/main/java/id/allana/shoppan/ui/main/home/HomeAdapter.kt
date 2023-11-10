package id.allana.shoppan.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import id.allana.shoppan.databinding.ItemProductAndFoodBinding
import id.allana.shoppan.network.response.DataItem

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeProductViewHolder>() {

    private var listItem: MutableList<DataItem> = mutableListOf()

    fun setListItems(listItem: List<DataItem>) {
        clearListItems()
        addListItems(listItem)
    }

    private fun clearListItems() {
        this.listItem.clear()
    }

    private fun addListItems(listItem: List<DataItem>) {
        this.listItem.addAll(listItem)
    }
    class HomeProductViewHolder(private val binding: ItemProductAndFoodBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: DataItem) {
            binding.tvTitle.text = item.name
            binding.tvPrice.text = item.price.toString()
            binding.tvSellerName.text = item.seller

            itemView.setOnClickListener {
                val actionToDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    nameProduct = item.name,
                    priceProduct = item.price,
                    sellerName = item.seller,
                    descriptionProduct = item.description
                )
                it.findNavController().navigate(actionToDetail)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeProductViewHolder {
        val binding = ItemProductAndFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        holder.bindView(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}