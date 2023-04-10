package com.example.remindersapp

import android.icu.util.Calendar
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

        fun getHourIntegerFormat(time: String): Int {
            return time.split(":")[0].toInt()
        }

        fun getMinutesIntegerFormat(time: String): Int {
            return time.split(":")[1].toInt()
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

        fun getReminderTimeInCalendarFormat(reminder: Reminder): Calendar {
            return Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, getHourIntegerFormat(reminder.time))
                set(Calendar.MINUTE, getMinutesIntegerFormat(reminder.time))
                set(Calendar.SECOND, 0)
            }
        }
    }
}