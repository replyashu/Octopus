package com.ashu.ocotopus.ui.hungry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.DishItem
import com.ashu.ocotopus.data.requests.AddDish
import com.ashu.ocotopus.repository.DishRepository
import com.ashu.ocotopus.repository.UserRepository
import com.ashu.ocotopus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HungryViewModel @Inject constructor(private val dishRepository: DishRepository,
                                          private val userRepository: UserRepository): ViewModel() {

    private val _resp = MutableLiveData<Resource<DishItem>>()

    val res : LiveData<Resource<DishItem>>
        get() = _resp

    fun postDish(addDish: AddDish?) = viewModelScope.launch {
        _resp.postValue(Resource.loading(null))

        try {
            dishRepository.addDish(addDish).let {
                if (it.isSuccessful) {
                    _resp.postValue(Resource.success(it.body()))
                } else {
                    _resp.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (error: Exception) {
            Log.d("error", error.toString())
        }
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}