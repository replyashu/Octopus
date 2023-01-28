package com.ashu.ocotopus.data.requests

import com.ashu.ocotopus.data.DishItem
import com.google.gson.annotations.SerializedName

data class AddDish (
    @SerializedName("dishName")
    val dishName: String? = null,
    @SerializedName("dishDescription")
    val dishDesciption: String? = null,
    @SerializedName("dishUrl")
    val dishUrl: String? = null,
    @SerializedName("userId")
    val userId: String? = null
)