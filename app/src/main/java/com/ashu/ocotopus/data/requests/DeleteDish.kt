package com.ashu.ocotopus.data.requests

import com.google.gson.annotations.SerializedName

data class DeleteDish(
    @SerializedName("userUuid")
    private val userId: String? = null,
    @SerializedName("dishId")
    private val dishId: Long = 0
)
