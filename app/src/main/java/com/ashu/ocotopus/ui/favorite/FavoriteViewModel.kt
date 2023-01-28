package com.ashu.ocotopus.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.DishItem
import com.ashu.ocotopus.data.requests.DeleteDish
import com.ashu.ocotopus.repository.DishRepository
import com.ashu.ocotopus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val dishRepository: DishRepository) : ViewModel() {

    private val _favoriteDish = MutableLiveData<Resource<Dish>>()
    private val _deleteDish = MutableLiveData<Resource<DishItem>>()

    val favoriteDish : LiveData<Resource<Dish>>
        get() = _favoriteDish

    val deleteDish: LiveData<Resource<DishItem>>
        get() = _deleteDish


    fun fetchFavoriteDish(userId: String?) = viewModelScope.launch {
        _favoriteDish.postValue(Resource.loading(null))

        try {
            dishRepository.fetchFavorites(userId).let {
                if (it.isSuccessful) {
                    _favoriteDish.postValue(Resource.success(it.body()))
                } else {
                    _favoriteDish.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            Log.d("some error has occurred", e.message.toString())
        }
    }

    fun deleteFavoriteDish(userId: String?, dishId: Long) = viewModelScope.launch {
        _deleteDish.postValue(Resource.loading(null))
        try {
            dishRepository.removeFavorite(DeleteDish(userId, dishId)).let {
                if (it.isSuccessful) {
                    _deleteDish.postValue(Resource.success(it.body()))
                } else {
                    _deleteDish.postValue(Resource.error(it.message(), null))
                }
            }
        } catch (e: java.lang.Exception) {
            Log.d("dish deletion failed", e.stackTraceToString())
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


}