package com.example.pexipconference.screens.conference

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class ScreenSharingService : Service() {

    // TODO (01) Define onCreate() that creates the notification channel and starts the foreground service

    // TODO (02) Define onDestroy() that stops the foreground service

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

    // TODO (03) Define a companion object with the NOTIFICATION_CHANNEL_ID and NOTIFICATION_ID

}