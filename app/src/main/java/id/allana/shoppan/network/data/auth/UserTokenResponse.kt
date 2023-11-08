package id.allana.shoppan.network.data.auth

import com.google.gson.annotations.SerializedName

data class UserTokenResponse(

	@field:SerializedName("token")
	val token: String
)
