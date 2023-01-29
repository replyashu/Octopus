package com.ashu.ocotopus.api.user

import com.ashu.ocotopus.data.ProfileUser
import com.ashu.ocotopus.data.requests.NotificationToken
import com.ashu.ocotopus.data.requests.RegisterUser
import com.ashu.ocotopus.data.responses.RegisterResponse
import com.google.firebase.firestore.auth.User
import retrofit2.Response

interface UserAPiHelper {

    suspend fun registerNewUser(registerUser: RegisterUser): Response<RegisterResponse>

    suspend fun updateNotificationToken(notificationToken: NotificationToken): Response<Boolean>

    suspend fun sendNotificationToAll(): Response<Boolean>

    suspend fun getUserProfileData(userId: String?): Response<ProfileUser>
}