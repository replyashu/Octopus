package com.ashu.ocotopus.data.requests

import com.google.gson.annotations.SerializedName

data class MarkFavoriteDish (
    @SerializedName("userUuid")
    val user_id: String? = null,
    @SerializedName("position")
    val dishPosition: Int = 0
)
