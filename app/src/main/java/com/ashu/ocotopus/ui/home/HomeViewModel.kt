package com.ashu.ocotopus.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.repository.MainRepository
import com.ashu.ocotopus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _resp = MutableLiveData<Resource<Dish>>()

    val res : LiveData<Resource<Dish>>
        get() = _resp

    init {
        getDishes()
    }

    private fun getDishes() = viewModelScope.launch {
        _resp.postValue(Resource.loading(null))

        try {
            mainRepository.fetchDishes().let {
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

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}