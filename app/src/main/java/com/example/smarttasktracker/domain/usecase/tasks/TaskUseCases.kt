package com.example.smarttasktracker.domain.usecase.tasks

import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasks @Inject constructor(private val tasksRepository: TasksRepository) {
    operator fun invoke(): Flow<List<TaskItem>> =
        tasksRepository.getAllTasks()
}

class GetTaskById @Inject constructor(private val tasksRepository: TasksRepository) {
    suspend operator fun invoke(taskId: Int): TaskItem =
        tasksRepository.getTaskById(taskId)
}

class DeleteTask @Inject constructor(private val tasksRepository: TasksRepository) {
    suspend operator fun invoke(task: TaskItem) {
        tasksRepository.deleteTask(task)
    }
}

class InsertTask @Inject constructor(private val tasksRepository: TasksRepository) {
    suspend operator fun invoke(task: TaskItem) {
        tasksRepository.insertTask(task)
    }
}

class UpdateTask @Inject constructor(private val tasksRepository: TasksRepository) {
    suspend operator fun invoke(task: TaskItem) {
        tasksRepository.updateTask(task)
    }
}

data class TaskUseCases @Inject constructor(
    val getAllTasks: GetAllTasks,
    val getTaskById: GetTaskById,
    val deleteTask: DeleteTask,
    val insertTask: InsertTask,
    val updateTask: UpdateTask,
)