package id.allana.shoppan.network.response

import com.google.gson.annotations.SerializedName

data class BaseNullDataResponse(

	@field:SerializedName("data")
	val data: Any,

	@field:SerializedName("meta")
	val meta: Any,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: Any,

	@field:SerializedName("status")
	val status: Boolean
)
