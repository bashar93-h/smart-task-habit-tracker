package com.example.smarttasktracker.data.datasources.local.repository

import com.example.smarttasktracker.core.notification.ReminderScheduler
import com.example.smarttasktracker.data.datasources.local.dao.TaskDao
import com.example.smarttasktracker.data.datasources.local.mapper.toDomain
import com.example.smarttasktracker.data.datasources.local.mapper.toEntity
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val reminderScheduler: ReminderScheduler
) : TasksRepository {
    override fun getAllTasks(): Flow<List<TaskItem>> =
        taskDao.getAllTasks().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun getTaskById(taskId: Int): TaskItem =
        taskDao.getTaskById(taskId).toDomain()

    override suspend fun deleteTask(task: TaskItem) {
        taskDao.deleteTask(task.toEntity())
        reminderScheduler.cancelTaskReminder(task.id)
    }

    override suspend fun insertTask(task: TaskItem) {
        taskDao.insertTask(task.toEntity())
        reminderScheduler.scheduleTaskReminder(task)
    }

    override suspend fun updateTask(task: TaskItem) {
        taskDao.updateTask(task.toEntity())
        reminderScheduler.cancelTaskReminder(task.id)
        reminderScheduler.scheduleTaskReminder(task)
    }
}