package com.example.smarttasktracker.presentation.screens.motivation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme

@Composable
fun MotivationScreen(navController: NavController?) {
    Scaffold(topBar = {
        AppTopBar("Motivation", onBackClick = { navController?.popBackStack() })
    }, bottomBar = { AppBottomBar(navController) }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { }
    }
}


@Preview
@Composable
fun MotivationScreenPreview() {
    SmartTaskTrackerTheme() {
        MotivationScreen(null)
    }
}
