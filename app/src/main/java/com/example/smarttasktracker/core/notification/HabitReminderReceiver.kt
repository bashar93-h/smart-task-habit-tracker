package com.example.smarttasktracker.core.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class HabitReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val habitId = intent.getIntExtra("habit_id", -1)
        val habitTitle = intent.getStringExtra("habit_title") ?: "Habit Reminder"
        val habitTime = intent.getStringExtra("habit_time") ?: ""

        val notification = NotificationHelper.buildNotification(
            context = context,
            title = "⏰ $habitTitle",
            message = "Time to do your habit at $habitTime"
        )

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(habitId + 100_000, notification)

        ReminderScheduler(context).scheduleHabitReminder(habitId, habitTitle, habitTime)

    }
}

// +100_000 is just for making sure habit notification don't overwrite task notification
// if we reuse the same ID it will replace the old notification