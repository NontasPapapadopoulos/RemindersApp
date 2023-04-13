package com.example.remindersapp


import android.annotation.SuppressLint
import android.app.Application
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ReminderViewModel (application: Application,
                         private val repository: ReminderRepository): AndroidViewModel(application) {

    private val notificationHandler = NotificationHandler(application)
    val allReminders : LiveData<List<Reminder>>

    init {
        allReminders = repository.allReminders
        notificationHandler.createNotificationChannel()
    }


    fun insert(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(reminder)
        val newReminder = repository.getLastReminder()
        notificationHandler.registerNotification(newReminder)
    }


    fun delete(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(reminder)
        notificationHandler.deleteNotification(reminder)

    }

    fun update(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(reminder)
        notificationHandler.updateNotification(reminder)
    }

    fun deleteAllReminders() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllReminders()
        notificationHandler.deleteAllNotifications(allReminders.value as ArrayList<Reminder>)
    }
}

