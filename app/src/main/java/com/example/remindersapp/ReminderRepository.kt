package com.example.remindersapp

import androidx.lifecycle.LiveData



class ReminderRepository (private val reminderDAO: ReminderDAO) {

    val allReminders = reminderDAO.getAllReminders()

    suspend fun insert(reminder: Reminder) {
        reminderDAO.insert(reminder)
    }

    suspend fun update(reminder: Reminder) {
        reminderDAO.update(reminder)
    }

    suspend fun delete(reminder: Reminder) {
        reminderDAO.delete(reminder)
    }

    suspend fun deleteAllReminders() {
        reminderDAO.deleteAllReminders()
    }
}