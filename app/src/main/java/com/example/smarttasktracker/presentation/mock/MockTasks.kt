package com.example.smarttasktracker.presentation.mock

import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem
import java.time.LocalDate


val mockTasks = listOf(
    TaskItem(
        1,
        "Morning Workout",
        "",
        "07:00 AM",
        "Health",
        false,
        Priority.HIGH,
        LocalDate.now(),
        "",
        ""
    ),
    TaskItem(
        2,
        "Team Standup",
        "",
        "09:30 AM",
        "Work",
        false,
        Priority.HIGH,
        LocalDate.now(),
        "",
        ""
    ),
    TaskItem(
        3,
        "Read 20 pages",
        "",
        "12:00 PM",
        "Personal",
        true,
        Priority.MEDIUM,
        LocalDate.now(),
        "",
        ""
    ),
    TaskItem(
        4,
        "Grocery Shopping",
        "",
        "05:00 PM",
        "Personal",
        false,
        Priority.LOW,
        LocalDate.now().plusDays(1),
        "",
        ""
    ),
    TaskItem(
        5,
        "Evening Walk",
        "",
        "07:00 PM",
        "Health",
        false,
        Priority.MEDIUM,
        LocalDate.now().plusDays(1),
        "",
        ""
    ),
    TaskItem(
        6,
        "Project Review",
        "",
        "10:00 AM",
        "Work",
        false,
        Priority.HIGH,
        LocalDate.now().plusDays(2),
        "",
        ""
    ),
    TaskItem(
        7,
        "Call Mom",
        "",
        "03:00 PM",
        "Personal",
        false,
        Priority.MEDIUM,
        LocalDate.now().plusDays(3),
        "",
        ""
    ),
    TaskItem(
        8,
        "Dentist Appointment",
        "",
        "11:00 AM",
        "Health",
        false,
        Priority.HIGH,
        LocalDate.now().plusDays(5),
        "",
        ""
    ),
    TaskItem(
        id = 9,
        title = "Morning Workout",
        description = "30 minutes of cardio followed by strength training",
        time = "07:00 AM",
        category = "Health",
        isCompleted = false,
        priority = Priority.HIGH,
        date = LocalDate.now(),
        notes = "Don't skip leg day. Remember to stretch after.",
        reminder = "30 min before"
    ),
    TaskItem(
        id = 10,
        title = "Team Standup",
        description = "Daily sync with the engineering team",
        time = "09:30 AM",
        category = "Work",
        isCompleted = false,
        priority = Priority.HIGH,
        date = LocalDate.now(),
        notes = "Prepare update on the current sprint progress.",
        reminder = "15 min before"
    ),
)