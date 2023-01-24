package com.ashu.ocotopus.api.user

import com.ashu.ocotopus.data.requests.NotificationToken
import com.ashu.ocotopus.data.requests.RegisterUser
import com.ashu.ocotopus.data.responses.RegisterResponse
import retrofit2.Response

interface UserAPiHelper {

    suspend fun registerNewUser(registerUser: RegisterUser): Response<RegisterResponse>

    suspend fun updateNotificationToken(notificationToken: NotificationToken): Response<Boolean>
}