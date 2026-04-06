package com.example.smarttasktracker.presentation.screens.statistics

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.screens.settings.SettingsScreen
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme

@Composable
fun StatisticsScreen(navController: NavController?) {
    Scaffold(topBar = {
        AppTopBar("Statistics", onBackClick = { navController?.popBackStack() })
    }, bottomBar = {}) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { }
    }
}

@Preview
@Composable
fun StatisticsScreenPreview() {
    SmartTaskTrackerTheme() {
        StatisticsScreen(null)
    }
}
