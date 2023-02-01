package com.ashu.ocotopus.data

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EditProfileData (
    var email: String? = null,
    var name: String? = null,
    var phoneNumber: String? = null,
    var profilePhoto: String? = null,
    var transferImage: Bitmap? = null
): Serializable