package com.ashu.ocotopus.api

import com.ashu.ocotopus.data.Dish
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("http://10.0.2.2:8081/dish")
    suspend fun fetchDishes() : Response<Dish>
}