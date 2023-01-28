package com.ashu.ocotopus.api.dish

import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.DishItem
import com.ashu.ocotopus.data.requests.AddDish
import com.ashu.ocotopus.data.requests.DeleteDish
import com.ashu.ocotopus.data.requests.MarkFavoriteDish
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.data.responses.DishRating
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface DishApiHelper {

    suspend fun fetchDishes(): Response<Dish>

    suspend fun rateDish(rateDish: RateDish): Response<DishRating>

    suspend fun markAsFavorite(markFavoriteDish: MarkFavoriteDish): Response<Boolean>

    suspend fun fetchFavorites(userId: String?): Response<Dish>

    suspend fun deleteFavorite(deleteDish: DeleteDish): Response<DishItem>

    suspend fun addDish(addDish: AddDish?): Response<DishItem>

}