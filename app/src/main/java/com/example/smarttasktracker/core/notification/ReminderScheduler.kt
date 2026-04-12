package com.example.smarttasktracker.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.smarttasktracker.domain.model.TaskItem
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class ReminderScheduler(private val context: Context) {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun scheduleTaskReminder(task: TaskItem) {
        if (task.reminder == "None") return
        val triggerTime = calculateTriggerTime(task) ?: return

        val intent = Intent(context, TaskReminderReceiver::class.java).apply {
            putExtra("task_id", task.id)
            putExtra("task_title", task.title)
            putExtra("task_time", task.time)
        }
        // permission for the system to execute your intent later
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
        }
    }

    fun cancelTaskReminder(taskId: Int) {
        val intent = Intent(context, TaskReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    private fun calculateTriggerTime(task: TaskItem): Long? {
        val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
        val taskTime = LocalTime.parse(task.time, formatter)
        val taskDateTime = LocalDateTime.of(task.date, taskTime)

        val reminderDateTime = taskDateTime.minus(
            when (task.reminder) {
                "5 min before" -> java.time.Duration.ofMinutes(5)
                "15 min before" -> java.time.Duration.ofMinutes(15)
                "30 min before" -> java.time.Duration.ofMinutes(30)
                "1 hour before" -> java.time.Duration.ofHours(1)
                "2 hours before" -> java.time.Duration.ofHours(2)
                "1 day before" -> java.time.Duration.ofDays(1)
                "At time of task" -> java.time.Duration.ZERO
                else -> null
            }
        )

        if (reminderDateTime.isBefore(LocalDateTime.now())) return null

        return reminderDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

}