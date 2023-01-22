package com.ashu.ocotopus.api.dish

import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.requests.MarkFavoriteDish
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.data.responses.DishRating
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DishApiService {

    @GET("dish")
    suspend fun fetchDishes(): Response<Dish>

    @POST("dish/rate")
    suspend fun rateDish(@Body rateDish: RateDish): Response<DishRating>

    @POST("dish/mark_favorite")
    suspend fun markFavorite(@Body markFavoriteDish: MarkFavoriteDish): Response<Boolean>
}