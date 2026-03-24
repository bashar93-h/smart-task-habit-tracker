package com.example.smarttasktracker.presentation.mock

import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem

val mockTasks = listOf(
    TaskItem(1, "Morning Workout", "07:00 AM", "Health", false, Priority.HIGH),
    TaskItem(2, "Team Standup", "09:30 AM", "Work", false, Priority.HIGH),
    TaskItem(3, "Read 20 pages", "12:00 PM", "Personal", true, Priority.MEDIUM),
    TaskItem(4, "Grocery Shopping", "05:00 PM", "Personal", false, Priority.LOW),
    TaskItem(5, "Evening Walk", "07:00 PM", "Health", false, Priority.MEDIUM),
)