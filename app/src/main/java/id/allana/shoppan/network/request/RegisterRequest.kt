package id.allana.shoppan.network.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("school")
    val school: String,

    @field:SerializedName("address")
    val address: String
)
