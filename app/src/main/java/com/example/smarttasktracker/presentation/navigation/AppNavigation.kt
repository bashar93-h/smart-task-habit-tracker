package com.example.smarttasktracker.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.smarttasktracker.presentation.mock.mockTasks
import com.example.smarttasktracker.presentation.screens.about.AboutScreen
import com.example.smarttasktracker.presentation.screens.addEditTask.AddEditTaskScreen
import com.example.smarttasktracker.presentation.screens.addHabit.AddHabitScreen
import com.example.smarttasktracker.presentation.screens.habits.HabitsScreen
import com.example.smarttasktracker.presentation.screens.home.HomeScreen
import com.example.smarttasktracker.presentation.screens.motivation.MotivationScreen
import com.example.smarttasktracker.presentation.screens.notification.NotificationScreen
import com.example.smarttasktracker.presentation.screens.savedQuotes.SavedQuotesScreen
import com.example.smarttasktracker.presentation.screens.settings.SettingsScreen
import com.example.smarttasktracker.presentation.screens.splash.SplashScreen
import com.example.smarttasktracker.presentation.screens.statistics.StatisticsScreen
import com.example.smarttasktracker.presentation.screens.tasks.details.TaskDetailsScreen
import com.example.smarttasktracker.presentation.screens.tasks.list.TasksScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val tasks = remember { mockTasks.toMutableStateList() }

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route, exitTransition = { fadeOut(animationSpec = tween(500)) }) {
            SplashScreen(navController)
        }
        composable(Screen.Home.route, enterTransition = { fadeIn(animationSpec = tween(500)) }) {
            HomeScreen(navController)
        }
        composable(Screen.Tasks.route) {
            TasksScreen(tasks, navController)
        }
        composable(
            Screen.TaskDetails.route,
            arguments = listOf(navArgument("taskId") {
                type =
                    NavType.IntType
            }),
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable
            val task = tasks.find { it.id == taskId } ?: return@composable
            TaskDetailsScreen(
                task = task,
                onEdit = {},
                onDelete = {
                    tasks.removeAll { it.id == taskId }
                    navController.popBackStack()
                },
                onCheckedChange = { checked ->
                    val index = tasks.indexOfFirst { it.id == taskId }
                    tasks[index] = tasks[index].copy(isCompleted = checked)

                },
                navController
            )
        }
        composable(Screen.AddEditTask.route) {
            AddEditTaskScreen(navController)
        }
        composable(Screen.Habits.route) {
            HabitsScreen(navController)
        }
        composable(Screen.AddHabit.route) {
            AddHabitScreen(navController)
        }
        composable(Screen.Motivation.route) {
            MotivationScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
        composable(Screen.Notification.route) {
            NotificationScreen(navController)
        }
        composable(Screen.Statistics.route) {
            StatisticsScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen(navController)
        }
        composable(Screen.SavedQuotes.route) {
            SavedQuotesScreen(navController)
        }
    }
}