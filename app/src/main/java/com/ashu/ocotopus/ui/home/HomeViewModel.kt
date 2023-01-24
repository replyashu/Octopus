package com.ashu.ocotopus.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.requests.MarkFavoriteDish
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.data.responses.DishRating
import com.ashu.ocotopus.repository.DishRepository
import com.ashu.ocotopus.repository.UserRepository
import com.ashu.ocotopus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dishRepository: DishRepository, private val userRepository: UserRepository) : ViewModel() {

    private val _resp = MutableLiveData<Resource<Dish>>()
    private val _rating = MutableLiveData<Resource<DishRating>>()
    private val _mark = MutableLiveData<Resource<Boolean>>()


    val res : LiveData<Resource<Dish>>
        get() = _resp

    val rating: LiveData<Resource<DishRating>>
        get() = _rating

    val mark: LiveData<Resource<Boolean>>
        get() = _mark

    fun getDishes() = viewModelScope.launch {
        _resp.postValue(Resource.loading(null))

        try {
            dishRepository.fetchDishes().let {
                if (it.isSuccessful) {
                    _resp.postValue(Resource.success(it.body()))
                } else {
                    _resp.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (error: Exception) {
            Log.d("errorr", error.toString())
        }
    }

    fun sendNotificationToAll() = viewModelScope.launch {
        userRepository.sendNotifications()
    }

    fun rateDish(rateDish: RateDish) = viewModelScope.launch {
        _rating.postValue(Resource.loading(null))
        try {
            dishRepository.rateDish(rateDish). let {
                if (it.isSuccessful) {
                    _rating.postValue(Resource.success(it.body()))
                } else {
                    _rating.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (error: Exception) {
            Log.d("errorr", error.toString())
        }

    }

    fun markAsFavorite(userUid: String?, position: Int) = viewModelScope.launch {
        _mark.postValue(Resource.loading(null))
        try {
            dishRepository.markAsFavorite(MarkFavoriteDish(userUid, position)).let {
                if (it.isSuccessful) {
                    _mark.postValue(Resource.success(it.body()))
                } else {
                    _mark.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (error: Exception) {
            Log.d("errorr", error.toString())
        }

    }
}