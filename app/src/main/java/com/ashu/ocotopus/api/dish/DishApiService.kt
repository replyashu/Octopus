package com.ashu.ocotopus.api.dish

import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.data.responses.DishRating
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DishApiService {

    @GET("http://10.0.2.2:8083/dish")
    suspend fun fetchDishes(): Response<Dish>

    @POST("http://10.0.2.2:8083/dish/rate")
    suspend fun rateDish(@Body rateDish: RateDish): Response<DishRating>
}