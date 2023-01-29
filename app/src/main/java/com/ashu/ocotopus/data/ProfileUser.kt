package com.ashu.ocotopus.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileUser(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("userPhone")
    val phoneNumber: String? = null,
    @SerializedName("imageUrl")
    val profilePhoto: String? = null,
    @SerializedName("isSubscribed")
    val isSubscribed: Boolean? = false,
    @SerializedName("mediumOfRegistration")
    val mediumOfRegistration: String = "google_android",
    @SerializedName("isGuestUser")
    val isGuestUser: Boolean? = null,
) : Parcelable