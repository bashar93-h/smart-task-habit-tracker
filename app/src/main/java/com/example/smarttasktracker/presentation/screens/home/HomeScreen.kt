package com.example.smarttasktracker.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar

@Composable
fun HomeScreen(navController: NavController?) {
    Scaffold(topBar = {
        AppTopBar("Smart Tracker", onMenuClick = {}, onQuoteClick = {})
    }, bottomBar = { AppBottomBar(navController) }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { }
    }
}

@Preview
@Composable
fun HomeScreenPreview() = HomeScreen(null)