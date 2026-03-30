package com.example.smarttasktracker.presentation.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")
    object Home : Screen("home")
    object Tasks : Screen("tasks")
    object TaskDetails : Screen("task_details/{taskId}") {
        fun createRoute(taskId: Int) = "task_details/${taskId}"
    }
    object AddEditTask : Screen("add_edit_task")
    object Habits : Screen("habits")
    object AddHabit : Screen("add_habit")
    object Motivation : Screen("motivation")
    object Settings : Screen("settings")
    object Notification : Screen("notification")
    object About : Screen("about")
    object Statistics : Screen("statistics")
    object SavedQuotes : Screen("saved_quotes")
}