package com.example.pexipconference.screens.conference

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.pexipconference.R
import com.google.android.material.button.MaterialButton

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("audioMuted")
fun audioMuted(button: MaterialButton, muted: Boolean) {
    if (muted) {
        button.icon = button.context.getDrawable(R.drawable.mic_off_icon)
    } else {
        button.icon = button.context.getDrawable(R.drawable.mic_icon)
    }
}

@BindingAdapter("videoMuted")
fun videoMuted(button: MaterialButton, muted: Boolean) {
    if (muted) {
        button.icon = button.context.getDrawable(R.drawable.videocam_off_icon)
    } else {
        button.icon = button.context.getDrawable(R.drawable.videocam_icon)
    }
}

@BindingAdapter("enabled")
fun enabled(button: MaterialButton, enabled: Boolean) {
    if (enabled) {
        button.setIconTintResource(R.color.blue_200)
    } else {
        button.setIconTintResource(R.color.white)
    }
}