package com.ashu.ocotopus.repository

import com.ashu.ocotopus.api.dish.DishApiHelper
import com.ashu.ocotopus.data.requests.MarkFavoriteDish
import com.ashu.ocotopus.data.requests.RateDish
import javax.inject.Inject

class DishRepository @Inject constructor(private val dishApiHelper: DishApiHelper) {

    suspend fun fetchDishes() = dishApiHelper.fetchDishes()

    suspend fun rateDish(rateDish: RateDish) = dishApiHelper.rateDish(rateDish = rateDish)

    suspend fun markAsFavorite(markFavoriteDish: MarkFavoriteDish) = dishApiHelper.markAsFavorite(markFavoriteDish)
}