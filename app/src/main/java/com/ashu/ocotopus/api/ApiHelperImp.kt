package com.ashu.ocotopus.api

import com.ashu.ocotopus.data.Dish
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImp @Inject constructor(private val apiService: ApiService): ApiHelper {

    override suspend fun fetchDishes(): Response<Dish> = apiService.fetchDishes()
}