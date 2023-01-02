package com.ashu.ocotopus.api

import com.ashu.ocotopus.data.Dish
import retrofit2.Response

interface ApiHelper {

    suspend fun fetchDishes(): Response<Dish>

}