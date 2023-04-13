package com.example.remindersapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ReminderViewModelFactory(private val application: Application,
private val repository: ReminderRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return ReminderViewModel(application, repository) as T
    }
}