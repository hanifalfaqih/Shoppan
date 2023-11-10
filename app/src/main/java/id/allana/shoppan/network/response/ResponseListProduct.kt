package id.allana.shoppan.network.response

import com.google.gson.annotations.SerializedName

data class ResponseListProduct(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: Any,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataItem(

	@field:SerializedName("seller")
	val seller: String,

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

	@field:SerializedName("uuid")
	val uuid: String
)

data class Meta(

	@field:SerializedName("totalData")
	val totalData: Int,

	@field:SerializedName("nextPage")
	val nextPage: Any,

	@field:SerializedName("pageSize")
	val pageSize: Int,

	@field:SerializedName("prevPage")
	val prevPage: Any,

	@field:SerializedName("currentPage")
	val currentPage: Int
)
