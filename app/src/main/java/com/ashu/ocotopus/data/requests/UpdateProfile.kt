package com.ashu.ocotopus.data.requests

import com.ashu.ocotopus.data.ProfileUser
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UpdateProfile(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phoneNumber")
    val phoneNumber: String? = null,
    @SerializedName("isSubscribed")
    val isSubscribed: Boolean? = false,
    @SerializedName("mediumOfRegistration")
    val mediumOfRegistration: String = "google_android",
    @SerializedName("isGuestUser")
    val isGuestUser: Boolean? = null,
    val userId: String? = null
) : Serializable
