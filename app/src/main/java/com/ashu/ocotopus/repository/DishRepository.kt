package com.ashu.ocotopus.repository

import com.ashu.ocotopus.api.ApiHelper
import com.ashu.ocotopus.data.requests.RateDish
import javax.inject.Inject

class DishRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun fetchDishes() = apiHelper.fetchDishes()

    suspend fun rateDish(rateDish: RateDish) = apiHelper.rateDish(rateDish = rateDish)
}