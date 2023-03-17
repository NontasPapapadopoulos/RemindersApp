package com.example.remindersapp

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ReminderRepository @Inject constructor(private val reminderDAO: ReminderDAO) {



    val allReminders: Flow<List<Reminder>> = reminderDAO.getAllReminders()

    @WorkerThread
    suspend fun insert(reminder: Reminder) {
        reminderDAO.insert(reminder)
    }

    @WorkerThread
    suspend fun update(reminder: Reminder) {
        reminderDAO.update(reminder)
    }

    @WorkerThread
    suspend fun delete(reminder: Reminder) {
        reminderDAO.delete(reminder)
    }

    @WorkerThread
    suspend fun deleteAllReminders() {
        reminderDAO.deleteAllReminders()
    }
}