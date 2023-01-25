package com.ashu.ocotopus.ui.favorite

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.responses.DishRating
import com.ashu.ocotopus.repository.DishRepository
import com.ashu.ocotopus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val dishRepository: DishRepository) : ViewModel() {

    private val _favoriteDish = MutableLiveData<Resource<Dish>>()

    val favoriteDish : LiveData<Resource<Dish>>
        get() = _favoriteDish


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

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


}