package com.ashu.ocotopus.api.user

import com.ashu.ocotopus.data.requests.NotificationToken
import com.ashu.ocotopus.data.requests.RegisterUser
import com.ashu.ocotopus.data.responses.RegisterResponse
import retrofit2.Response
import javax.inject.Inject

class UserApiHelperImp @Inject constructor(private val userApiService: UserApiService): UserAPiHelper {

    override suspend fun registerNewUser(registerUser: RegisterUser): Response<RegisterResponse> =
        userApiService.registerUser(registerUser)

    override suspend fun updateNotificationToken(notificationToken: NotificationToken): Response<Boolean> =
        userApiService.updateNotificationToken(notificationToken)

    override suspend fun sendNotificationToAll(): Response<Boolean> = userApiService.sendNotificationToAll()
}