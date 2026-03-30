package com.example.smarttasktracker.presentation.screens.notification

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.screens.about.AboutScreen
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme

@Composable
fun NotificationScreen(navController: NavController?) {
    Scaffold(topBar = {
        AppTopBar("Notification", onBackClick = {navController?.popBackStack()})
    }, bottomBar = { AppBottomBar(navController) }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { }
    }
}


@Preview
@Composable
fun NotificationScreenPreview() {
    SmartTaskTrackerTheme() {
        NotificationScreen(null)
    }
}
