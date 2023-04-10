package com.example.remindersapp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.remindersapp.TimeUtil.Companion.getReminderTimeInCalendarFormat


class NotificationHandler(val context: Context?) {

    private lateinit var notificationManager: NotificationManager
    private var alarmManager: AlarmManager = context!!.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager


    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("reminderChannel",
                                             "reminderChannel",
                                              NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Description"

            notificationManager = context!!.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    fun registerNotification(reminder: Reminder) {

        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
        intent.putExtra("title", determineNotificationTitleFromReminderType(reminder))

        val pendingIntent = PendingIntent.getBroadcast(context,
                                                       reminder.id,
                                                       intent,
                                                       0)


        val calendar: Calendar = getReminderTimeInCalendarFormat(reminder)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent)

    }


    fun deleteNotification(reminder: Reminder) {
        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, reminder.id, intent, 0)
        alarmManager.cancel(pendingIntent)
        notificationManager.cancel(reminder.id)

        //Toast.makeText(context,"Alarm Canceled", Toast.LENGTH_SHORT).show()
    }

    fun updateNotification(reminder: Reminder) {
        deleteNotification(reminder)
        registerNotification(reminder)
    }

    fun deleteAllNotifications(reminders: ArrayList<Reminder>) {
        if(reminders.isNotEmpty()) {
            for (reminder in reminders) {
                val intent = Intent(context, NotificationBroadcastReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(context, reminder.id, intent, 0)
                alarmManager.cancel(pendingIntent)
                notificationManager.cancel(reminder.id)
            }
         //   Toast.makeText(context,"All Alarms Canceled", Toast.LENGTH_SHORT).show()
        }
    }


    private fun determineNotificationTitleFromReminderType(reminder: Reminder): String {
        return if (reminder.type.uppercase() == ReminderType.WATER.name)
            "Time for Water"
        else
            "Time for Snack"
    }



    fun checkNotifications(requestCode: Int) {
        val myIntent = Intent(context, NotificationBroadcastReceiver::class.java)

        val isWorking = PendingIntent.getBroadcast(
            context,
            requestCode,
            myIntent,
            PendingIntent.FLAG_NO_CREATE
        ) != null
        if (isWorking) {
            Log.i("alarm ", "is working")
        } else {
            Log.i("alarm ", "is not working")
        }
    }



}