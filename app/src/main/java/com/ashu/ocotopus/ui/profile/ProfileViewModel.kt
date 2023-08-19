package com.ashu.ocotopus.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.data.ProfileUser
import com.ashu.ocotopus.data.requests.UpdateProfile
import com.ashu.ocotopus.repository.UserRepository
import com.ashu.ocotopus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _profile = MutableLiveData<Resource<ProfileUser>>()

    val profile: LiveData<Resource<ProfileUser>>
        get() = _profile

    private val _editProfile = MutableLiveData<Resource<ProfileUser>>()

    val editProfile: LiveData<Resource<ProfileUser>>
        get() = _editProfile

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

    fun updateProfile(profileUser: ProfileUser?) = viewModelScope.launch {
        _editProfile.postValue(Resource.loading(null))
        try {

//            val updateProfile = UpdateProfile(profileUser?.email,
//                profileUser?.name, profileUser?.phoneNumber, profileUser?.profilePhoto,
//                profileUser?.profileSrc, userId = userId, mediumOfRegistration = "android")
            userRepository.updateUserData(MultipartBody.Part.createFormData(
                name = "updateProfile", profileUser.toString()
            ), MultipartBody.Part.createFormData("userPhoto", profileUser?.imageFile?.name!!)).let {
                if (it.isSuccessful) {
                    _editProfile.postValue(Resource.success(it.body()))
                } else {
                    _editProfile.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            Log.d("edit error", e.message.toString())
    }
    }


}