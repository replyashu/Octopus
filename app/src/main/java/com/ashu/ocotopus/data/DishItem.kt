package com.ashu.ocotopus.data

data class DishItem(
    val dishDescription: String,
    val dishId: Long,
    val dishName: String,
    var dishRating: Double? = 0.0,
    val dishType: String,
    val dishUrl: String,
    var totalRatings: Long? = 0,
    val isFavorite: Boolean
)