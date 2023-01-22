package com.ashu.ocotopus.di

import com.ashu.ocotopus.api.dish.DishApiHelper
import com.ashu.ocotopus.api.dish.DishApiHelperImp
import com.ashu.ocotopus.api.dish.DishApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideDishApiService(retrofit: Retrofit) = retrofit.create(DishApiService::class.java)

    @Singleton
    @Provides
    fun provideDishApiHelper(dishApiHelper: DishApiHelperImp): DishApiHelper = dishApiHelper
}