package com.example.remindersapp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationBroadcastReceiver : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val builder = NotificationCompat.Builder(context!!, "reminderChannel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(intent!!.getStringExtra("title"))
            .setContentText(intent!!.getStringExtra("time"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(200, builder.build())
    }
}