package com.example.remindersapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

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
    fun getAllReminders(): Flow<List<Reminder>>
}