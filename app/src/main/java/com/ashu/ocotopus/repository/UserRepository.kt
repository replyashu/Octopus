package com.ashu.ocotopus.repository

import com.ashu.ocotopus.api.user.UserAPiHelper
import com.ashu.ocotopus.data.requests.NotificationToken
import com.ashu.ocotopus.data.requests.RegisterUser
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPiHelper: UserAPiHelper) {

    suspend fun registerUser(registerUser: RegisterUser) = userAPiHelper.registerNewUser(registerUser)

    suspend fun updateNotificationToken(notificationToken: NotificationToken) = userAPiHelper.updateNotificationToken(notificationToken)

}