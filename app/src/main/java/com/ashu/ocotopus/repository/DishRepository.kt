package com.ashu.ocotopus.repository

import com.ashu.ocotopus.api.dish.DishApiHelper
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.DishItem
import com.ashu.ocotopus.data.requests.AddDish
import com.ashu.ocotopus.data.requests.DeleteDish
import com.ashu.ocotopus.data.requests.MarkFavoriteDish
import com.ashu.ocotopus.data.requests.RateDish
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class DishRepository @Inject constructor(private val dishApiHelper: DishApiHelper) {

    suspend fun fetchDishes() = dishApiHelper.fetchDishes()

    suspend fun rateDish(rateDish: RateDish) = dishApiHelper.rateDish(rateDish = rateDish)

    suspend fun fetchFavorites(userId: String?) = dishApiHelper.fetchFavorites(userId)

    suspend fun markAsFavorite(markFavoriteDish: MarkFavoriteDish) = dishApiHelper.markAsFavorite(markFavoriteDish)

    suspend fun removeFavorite(deleteDish: DeleteDish) = dishApiHelper.deleteFavorite(deleteDish)

    suspend fun addDish(addDish: AddDish?) = dishApiHelper.addDish(addDish)
}