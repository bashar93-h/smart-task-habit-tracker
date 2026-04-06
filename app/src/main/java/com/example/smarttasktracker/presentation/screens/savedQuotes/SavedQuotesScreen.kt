package com.example.smarttasktracker.presentation.screens.savedQuotes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.screens.motivation.MotivationScreen
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme

@Composable
fun SavedQuotesScreen(navController: NavController?) {
    Scaffold(topBar = {
        AppTopBar("Saved Quotes", onBackClick = { navController?.popBackStack() })
    }, bottomBar = { }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { }
    }
}


@Preview
@Composable
fun SavedQuotesScreenPreview() {
    SmartTaskTrackerTheme() {
        SavedQuotesScreen(null)
    }
}
