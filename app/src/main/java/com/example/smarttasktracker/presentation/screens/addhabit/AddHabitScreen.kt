package com.example.smarttasktracker.presentation.screens.addhabit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddHabitScreen(navController: NavController) {
    Surface(modifier = Modifier.padding(1.dp)) {
        Text("Add Habit Screen")
    }
}