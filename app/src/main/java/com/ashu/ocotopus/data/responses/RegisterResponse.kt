package com.ashu.ocotopus.data.responses

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("profilePhoto")
    val profilePhoto: String? = null
)