package com.example.remindersapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
@HiltAndroidApp
class ReminderApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        ReminderDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        ReminderRepository(database.getReminderDao())
    }


}