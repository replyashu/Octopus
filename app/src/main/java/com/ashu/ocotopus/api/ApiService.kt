package com.ashu.ocotopus.api

import com.ashu.ocotopus.data.Dish
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json")
    @GET("http://10.0.2.2:8081/dish")
    suspend fun fetchDishes() : Response<Dish>
}