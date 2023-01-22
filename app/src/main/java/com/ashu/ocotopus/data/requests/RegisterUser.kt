package com.ashu.ocotopus.data.requests

import com.google.gson.annotations.SerializedName

data class RegisterUser(
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null
)
