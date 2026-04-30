package com.example.smarttasktracker.core.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.smarttasktracker.R
import androidx.core.app.NotificationCompat
import com.example.smarttasktracker.MainActivity

object NotificationHelper {
    const val CHANNEL_ID = "task_reminder_channel"
    const val CHANNEL_NAME = "Task Reminders"

    fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications for task and habit reminders"
            enableLights(true)
            enableVibration(true)
        }
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    fun buildNotification(context: Context, title: String, message: String): Notification {

        val intent = Intent(context, MainActivity::class.java).apply {
            // if the MainActivity exist on the top of the stack reuse it, don't create a new one
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.app_logo)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
    }
}

//With channels, the system lets users:
//Turn off specific types of notifications
//Change sound, vibration, importance
//Keep important alerts while muting annoying ones

//Logical grouping
//Channels force you to organize notifications into meaningful categories.
//
//For example in a chat app:
//
//messages_channel
//calls_channel
//system_updates_channel