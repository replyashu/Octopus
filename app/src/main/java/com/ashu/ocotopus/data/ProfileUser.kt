package com.ashu.ocotopus.data

import android.graphics.Bitmap
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.File
import java.io.Serializable

@Parcelize
data class ProfileUser(
    @SerializedName("userId")
    var userId: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("userPhone")
    var phoneNumber: String? = null,
    @SerializedName("imageUrl")
    var profilePhoto: String? = null,
    @SerializedName("isSubscribed")
    var isSubscribed: Boolean? = false,
    @SerializedName("mediumOfRegistration")
    var mediumOfRegistration: String = "google_android",
    @SerializedName("isGuestUser")
    var isGuestUser: Boolean? = null,
    @SerializedName("transferImage")
    var transferImage: Bitmap? = null,
    val imageFile: File?
) : Parcelable