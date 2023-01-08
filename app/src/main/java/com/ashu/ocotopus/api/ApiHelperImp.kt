package com.ashu.ocotopus.api

import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.data.responses.DishRating
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImp @Inject constructor(private val apiService: ApiService): ApiHelper {

    override suspend fun fetchDishes(): Response<Dish> = apiService.fetchDishes()

    override suspend fun rateDish(rateDish: RateDish): Response<DishRating> = apiService.rateDish(rateDish)
}