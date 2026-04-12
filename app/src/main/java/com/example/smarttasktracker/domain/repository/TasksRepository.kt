package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.domain.model.TaskItem
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getAllTasks(): Flow<List<TaskItem>>
    suspend fun getTaskById(taskId: Int): TaskItem
    suspend fun deleteTask(task: TaskItem)
    suspend fun insertTask(task: TaskItem)
    suspend fun updateTask(task: TaskItem)
}