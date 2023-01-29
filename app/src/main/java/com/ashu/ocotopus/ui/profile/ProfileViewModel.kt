package com.ashu.ocotopus.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.data.ProfileUser
import com.ashu.ocotopus.repository.UserRepository
import com.ashu.ocotopus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _profile = MutableLiveData<Resource<ProfileUser>>()

    val profile: LiveData<Resource<ProfileUser>>
        get() = _profile

    fun fetchProfileData(userId: String?) = viewModelScope.launch {
        _profile.postValue(Resource.loading(null))

        try {
            userRepository.fetchUserData(userId).let {
                if (it.isSuccessful) {
                    _profile.postValue(Resource.success(it.body()))
                } else {
                    _profile.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            Log.d("profile fetching failed", e.message.toString())
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text
}