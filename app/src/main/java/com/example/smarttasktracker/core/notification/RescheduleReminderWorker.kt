package com.example.smarttasktracker.core.notification

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.smarttasktracker.domain.repository.HabitsRepository
import com.example.smarttasktracker.domain.repository.TasksRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first


class RescheduleReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val tasksRepository: TasksRepository,
    private val habitsRepository: HabitsRepository
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val scheduler = ReminderScheduler(applicationContext)
        tasksRepository.getAllTasks().first().filter {
            !it.isCompleted && it.reminder != "None"
        }.forEach { scheduler.scheduleTaskReminder(it) }

        habitsRepository.getALlHabits().first().filter {
            it.reminderTime != "No Reminder"
        }.forEach { scheduler.scheduleHabitReminder(it) }

        return Result.success()
    }

    companion object {
        fun enqueue(context: Context) {
            // we create a one time request to run the worker
            val request = OneTimeWorkRequestBuilder<RescheduleReminderWorker>().build()
            // schedules the work with WorkManager
            WorkManager.getInstance(context).enqueue(request)
        }
    }
}

// Android clears alarms after reboot
// Worker : background task that runs even if the app is closed
// Worker = CoroutineWorker but support coroutines (suspend)
// @Assisted mean these params are provided by WorkManager