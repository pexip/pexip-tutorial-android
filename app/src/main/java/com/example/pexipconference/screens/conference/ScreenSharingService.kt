package com.example.pexipconference.screens.conference

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.pexipconference.R

class ScreenSharingService : Service() {

    override fun onCreate() {
        super.onCreate()
        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Capturing Screen" ,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationChannel.lightColor = R.color.teal
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        val notification = Notification.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setOngoing(true)
            .setContentTitle(getString(R.string.screen_capturing_title))
            .setContentText(getString(R.string.screen_capturing_text))
            .setSmallIcon(R.drawable.ic_launcher_small)
            .build()
        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL_SCREEN_SHARING"
        const val NOTIFICATION_ID = 1
    }
}