package com.ashu.ocotopus.ui.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.Dish
import com.ashu.ocotopus.data.requests.RegisterUser
import com.ashu.ocotopus.data.responses.DishRating
import com.ashu.ocotopus.data.responses.RegisterResponse
import com.ashu.ocotopus.repository.UserRepository
import com.ashu.ocotopus.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private var showOneTapUI = true
    private val _register = MutableLiveData<Resource<RegisterResponse>>()

    val register: LiveData<Resource<RegisterResponse>>
        get() = _register

    fun registerUser(registerUser: RegisterUser) = viewModelScope.launch {
        _register.postValue(Resource.loading(null))
        try {
            userRepository.registerUser(registerUser). let {
                if (it.isSuccessful) {
                    if (it.code() == 208) {
                        _register.postValue(Resource.alreadyRegistered("already_registered", it.body()))
                    } else {
                        _register.postValue(Resource.success(it.body()))
                    }
                } else {
                    _register.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        } catch (e: Exception) {
            Log.d("Login Failed", "registration failed")
        }
    }

    fun getSigninRequest(clientKey: String): BeginSignInRequest {
        val signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                .setSupported(true)
                .build())
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(clientKey)
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()

        return signInRequest
    }

    fun loginVerification(oneTapClient: SignInClient, data: Intent?) {
        val credential = oneTapClient.getSignInCredentialFromIntent(data)
        val idToken = credential.googleIdToken
        val username = credential.id
        val password = credential.password
        when {
            idToken != null -> {
                // Got an ID token from Google. Use it to authenticate
                // with your backend.
                registerUser(RegisterUser(idToken))
                Log.d("Login3", "Got ID token." + idToken)
            }
            password != null -> {
                // Got a saved username and password. Use them to authenticate
                // with your backend.
                Log.d("Login4", "Got password.")
            }
            else -> {
                // Shouldn't happen.
                Log.d("Login5", "No ID token or password!")
            }
        }
    }

    fun handleLoginException(e: ApiException) {
        when (e.statusCode) {
            CommonStatusCodes.CANCELED -> {
                Log.d("Login6", "One-tap dialog was closed.")
                // Don't re-prompt the user.
                showOneTapUI = false
            }
            CommonStatusCodes.NETWORK_ERROR -> {
                Log.d("Login7", "One-tap encountered a network error.")
                // Try again or just ignore.
            }
            else -> {
                Log.d("Login8", "Couldn't get credential from result." +
                        " (${e.localizedMessage})")
            }
        }
    }
}