package com.ashu.ocotopus.api.user

import com.ashu.ocotopus.data.ProfileUser
import com.ashu.ocotopus.data.requests.NotificationToken
import com.ashu.ocotopus.data.requests.RegisterUser
import com.ashu.ocotopus.data.requests.UpdateProfile
import com.ashu.ocotopus.data.responses.RegisterResponse
import com.google.firebase.firestore.auth.User
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserApiService {

    @POST("user/save")
    suspend fun registerUser(@Body registerUser: RegisterUser): Response<RegisterResponse>

    @POST("user/enable-push")
    suspend fun updateNotificationToken(@Body notificationToken: NotificationToken): Response<Boolean>

    @POST("user/notify")
    suspend fun sendNotificationToAll(): Response<Boolean>

    @GET("user/profile")
    suspend fun fetchUserProfile(@Query("userId") userId: String?): Response<ProfileUser>

    @Multipart
    @POST("user/edit-profile")
    suspend fun updateUserProfile(@Part updateProfile: MultipartBody.Part?,
                    @Part userPhoto: MultipartBody.Part?): Response<ProfileUser>
}