package com.example.smarttasktracker.presentation.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.domain.usecase.tasks.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val useCases: TaskUseCases) : ViewModel() {
    private val _tasks = MutableStateFlow<List<TaskItem>>(emptyList())
    val tasks = _tasks.asStateFlow()

    private val _selectedTask = MutableStateFlow<TaskItem?>(null)
    val selectedTask = _selectedTask.asStateFlow()


    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            useCases.getAllTasks().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }

    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            _selectedTask.value = useCases.getTaskById(taskId)
        }
    }

    fun addTask(task: TaskItem) {
        viewModelScope.launch {
            useCases.insertTask(task)
        }
    }

    fun updateTask(task: TaskItem) {
        viewModelScope.launch {
            useCases.updateTask(task)
            if (_selectedTask.value?.id == task.id) {
                _selectedTask.value = task
            }
        }
    }

    fun deleteTask(task: TaskItem) {
        viewModelScope.launch {
            useCases.deleteTask(task)
        }
    }
}