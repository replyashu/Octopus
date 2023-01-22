package com.ashu.ocotopus.api.dish

import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.data.responses.DishRating
import retrofit2.Response

interface DishApiHelper {

    suspend fun fetchDishes(): Response<Dish>

    suspend fun rateDish(rateDish: RateDish): Response<DishRating>

}