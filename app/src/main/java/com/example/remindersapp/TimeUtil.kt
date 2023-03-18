package com.example.remindersapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimeUtil {

    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun convertStringTimeToLocalTime (time: String): LocalTime {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return LocalTime.parse(time, formatter)
        }

        fun convertStringTimeToInteger(time: String): Int {
            return time.split(":")[0].toInt()
        }

        fun parseTimeFromTimePicker(hour: Int, minute: Int): String {
            return "$hour:$minute"
        }
    }
}