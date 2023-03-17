package com.example.remindersapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_table")
data class Reminder(val time: String,
                    val type: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
