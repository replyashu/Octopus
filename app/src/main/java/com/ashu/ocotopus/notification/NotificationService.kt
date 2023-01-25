package com.ashu.ocotopus.notification

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ashu.ocotopus.ui.notification.CreateNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService: FirebaseMessagingService() {

    private val sharedpreferences: SharedPreferences by lazy { getSharedPreferences("preference_key", Context.MODE_PRIVATE) }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // handle notification
        Log.d("okhttp", message.data.toString() + message.notification)
        val builder = CreateNotification.buildNotification(this, message)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sharedpreferences.edit().putString("user_token", token).apply()
    }


}