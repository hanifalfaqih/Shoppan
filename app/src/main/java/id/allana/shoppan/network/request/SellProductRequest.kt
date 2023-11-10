package id.allana.shoppan.network.request

import com.google.gson.annotations.SerializedName

data class SellProductRequest(

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("slogan")
	val slogan: String
)
