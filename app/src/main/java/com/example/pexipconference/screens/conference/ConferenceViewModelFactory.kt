package com.example.pexipconference.screens.conference

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ConferenceViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConferenceViewModel::class.java)) {
            return ConferenceViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}