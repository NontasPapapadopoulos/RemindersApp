package com.example.remindersapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "reminder_table")
data class Reminder(
    var time: String,
    var type: String,
    val isActive: Boolean) : Serializable  {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}


enum class ReminderType {
    WATER,
    SNACK
}