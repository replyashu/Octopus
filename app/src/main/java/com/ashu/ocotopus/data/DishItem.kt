package com.ashu.ocotopus.data

data class DishItem(
    val dishDescription: String? = null,
    val dishId: Long = 0,
    val dishName: String? = null,
    var dishRating: Double? = 0.0,
    val dishType: String? = null,
    val dishUrl: String? = null,
    var totalRatings: Long? = 0,
    val isFavorite: Boolean? = false
)