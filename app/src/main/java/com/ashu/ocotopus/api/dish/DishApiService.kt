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
import retrofit2.http.*

interface DishApiService {

    @GET("dish")
    suspend fun fetchDishes(): Response<Dish>

    @POST("dish/rate")
    suspend fun rateDish(@Body rateDish: RateDish): Response<DishRating>

    @POST("dish/mark_favorite")
    suspend fun markFavorite(@Body markFavoriteDish: MarkFavoriteDish): Response<Boolean>

    @GET("/dish/get_favorite")
    suspend fun fetchFavorites(@Query("userId") userId: String?): Response<Dish>

    @POST("/dish/delete_favorite")
    suspend fun deleteFavorite(@Body deleteDish: DeleteDish): Response<DishItem>

    @POST("/dish/add_new")
    suspend fun addDish(@Body addDish: AddDish?): Response<DishItem>
}