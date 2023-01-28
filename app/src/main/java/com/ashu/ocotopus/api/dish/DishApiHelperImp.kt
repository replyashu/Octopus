package com.ashu.ocotopus.api.dish

import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.DishItem
import com.ashu.ocotopus.data.requests.AddDish
import com.ashu.ocotopus.data.requests.DeleteDish
import com.ashu.ocotopus.data.requests.MarkFavoriteDish
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.data.responses.DishRating
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class DishApiHelperImp @Inject constructor(private val dishApiService: DishApiService):
    DishApiHelper {

    override suspend fun fetchDishes(): Response<Dish> = dishApiService.fetchDishes()

    override suspend fun rateDish(rateDish: RateDish): Response<DishRating> = dishApiService.rateDish(rateDish)

    override suspend fun markAsFavorite(markFavoriteDish: MarkFavoriteDish):
            Response<Boolean> = dishApiService.markFavorite(markFavoriteDish)

    override suspend fun fetchFavorites(userId: String?): Response<Dish> =
        dishApiService.fetchFavorites(userId)

    override suspend fun deleteFavorite(deleteDish: DeleteDish): Response<DishItem>  =
        dishApiService.deleteFavorite(deleteDish)

    override suspend fun addDish(addDish: AddDish?):
            Response<DishItem> = dishApiService.addDish(addDish)
}