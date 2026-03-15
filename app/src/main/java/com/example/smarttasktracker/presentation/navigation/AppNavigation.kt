package com.example.smarttasktracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarttasktracker.presentation.screens.addedittask.AddEditTaskScreen
import com.example.smarttasktracker.presentation.screens.addhabit.AddHabitScreen
import com.example.smarttasktracker.presentation.screens.habits.HabitsScreen
import com.example.smarttasktracker.presentation.screens.home.HomeScreen
import com.example.smarttasktracker.presentation.screens.motivation.MotivationScreen
import com.example.smarttasktracker.presentation.screens.settings.SettingsScreen
import com.example.smarttasktracker.presentation.screens.splash.SplashScreen
import com.example.smarttasktracker.presentation.screens.taskdetails.TaskDetailsScreen
import com.example.smarttasktracker.presentation.screens.tasks.TasksScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Home.route) {
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
    }
}