package com.ashu.ocotopus.repository

import com.ashu.ocotopus.api.user.UserAPiHelper
import com.ashu.ocotopus.data.ProfileUser
import com.ashu.ocotopus.data.requests.NotificationToken
import com.ashu.ocotopus.data.requests.RegisterUser
import com.ashu.ocotopus.data.requests.UpdateProfile
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPiHelper: UserAPiHelper) {

    suspend fun registerUser(registerUser: RegisterUser) = userAPiHelper.registerNewUser(registerUser)

    suspend fun updateNotificationToken(notificationToken: NotificationToken) =
        userAPiHelper.updateNotificationToken(notificationToken)

    suspend fun sendNotifications() = userAPiHelper.sendNotificationToAll()

    suspend fun fetchUserData(userId: String?) = userAPiHelper.getUserProfileData(userId)

    suspend fun updateUserData(updateProfile: UpdateProfile?) =
        userAPiHelper.updateUserProfileData(updateProfile)

}