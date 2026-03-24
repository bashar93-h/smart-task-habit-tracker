package com.example.smarttasktracker.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.example.smarttasktracker.presentation.screens.taskDetails.TaskDetailsScreen
import com.example.smarttasktracker.presentation.screens.tasks.TasksScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route, exitTransition = { fadeOut(animationSpec = tween(500)) }) {
            SplashScreen(navController)
        }
        composable(Screen.Home.route, enterTransition = { fadeIn(animationSpec = tween(500)) }) {
            HomeScreen(navController)
        }
        composable(Screen.Tasks.route) {
            TasksScreen(navController)
        }
        composable(Screen.TaskDetails.route) {
            TaskDetailsScreen(navController)
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