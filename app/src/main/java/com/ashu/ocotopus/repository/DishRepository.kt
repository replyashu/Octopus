package com.ashu.ocotopus.repository

import com.ashu.ocotopus.api.ApiHelper
import javax.inject.Inject

class DishRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun fetchDishes() = apiHelper.fetchDishes()
}