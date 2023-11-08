package id.allana.shoppan.network.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<D>(

	@field:SerializedName("data")
	val data: D? = null,

	@field:SerializedName("meta")
	val meta: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("error")
	val error: String,

	@field:SerializedName("status")
	val status: Boolean
)
