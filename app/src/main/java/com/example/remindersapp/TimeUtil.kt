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
            var hour24 = hour.toString()
            var minute24 = minute.toString()
            if (hour < 10)
                hour24 = "0$hour"

            if (minute < 10)
                minute24 = "0$minute"

            return "$hour24:$minute24"
        }
    }
}