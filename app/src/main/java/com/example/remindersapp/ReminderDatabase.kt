package com.example.remindersapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Reminder::class], version = 1)
abstract class ReminderDatabase: RoomDatabase() {

    abstract fun getReminderDao(): ReminderDAO

    // Single object of database class
    companion object {
        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        fun getDatabase(context: Context): ReminderDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderDatabase::class.java,
                    "reminder_database")
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}