package com.ashu.ocotopus.data.requests

import com.google.gson.annotations.SerializedName

data class RateDish(
    @SerializedName("dishId")
    val dishId: Long,
    @SerializedName("dishRating")
    val dishRating: Float
)
