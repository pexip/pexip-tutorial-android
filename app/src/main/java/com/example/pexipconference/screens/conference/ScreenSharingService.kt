package com.example.pexipconference.screens.conference

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class ScreenSharingService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

}