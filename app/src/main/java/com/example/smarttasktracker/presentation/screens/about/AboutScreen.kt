package com.example.smarttasktracker.presentation.screens.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        AppTopBar("About", onMenuClick = {}, onQuoteClick = {})
    }, bottomBar = { AppBottomBar(navController) }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { }
    }
}