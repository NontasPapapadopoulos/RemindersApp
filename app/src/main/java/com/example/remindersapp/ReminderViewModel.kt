package com.example.remindersapp


import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ReminderViewModel (application: Application): AndroidViewModel(application) {


    val allReminders : LiveData<List<Reminder>>
    private val repository: ReminderRepository

    init {
        val dao = ReminderDatabase.getDatabase(application).getReminderDao()
        repository = ReminderRepository(dao)
        allReminders = repository.allReminders
    }


    fun insert(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(reminder)
    }

    fun delete(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(reminder)
    }

    fun update(reminder: Reminder) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(reminder)
    }

    fun deleteAllReminders() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllReminders()
    }
}

