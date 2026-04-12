package com.example.smarttasktracker.core.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TaskReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskId = intent.getIntExtra("task_id", -1)
        val taskTitle = intent.getStringExtra("task_title") ?: "Task Reminder"
        val taskTime = intent.getStringExtra("task_time") ?: ""

        val notification = NotificationHelper.buildNotification(
            context = context,
            title = "⏰ $taskTitle",
            message = "Your task is due at $taskTime"
        )

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(taskId, notification)
    }
}