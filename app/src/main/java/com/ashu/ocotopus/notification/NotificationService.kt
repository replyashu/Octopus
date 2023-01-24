package com.ashu.ocotopus.notification

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService: FirebaseMessagingService() {

    private val sharedpreferences: SharedPreferences by lazy { getSharedPreferences("preference_key", Context.MODE_PRIVATE) }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // handle notification
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sharedpreferences.edit().putString("user_token", token).apply()
    }
}