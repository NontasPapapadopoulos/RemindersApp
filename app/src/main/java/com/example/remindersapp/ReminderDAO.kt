package com.example.remindersapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReminderDAO {

    @Insert
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("DELETE FROM reminder_table")
    suspend fun deleteAllReminders()

    @Query("SELECT * FROM reminder_table ORDER BY id ASC")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Query("SELECT * FROM reminder_table WHERE id =  (SELECT MAX(id) FROM reminder_table)")
    suspend fun getLastReminder(): Reminder

}