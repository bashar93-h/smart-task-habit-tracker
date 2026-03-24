package com.example.smarttasktracker.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppDrawer
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.mock.staticQuote
import com.example.smarttasktracker.presentation.screens.home.components.QuoteCard
import com.example.smarttasktracker.presentation.screens.home.components.TodayActivitySection
import com.example.smarttasktracker.presentation.screens.home.components.TodayHabitsSection
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController?) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        AppDrawer(onNavigate = { route ->
            navController?.navigate(route)
            scope.launch { drawerState.close() }
        }, onClose = {})
    }) {
        Scaffold(topBar = {
            AppTopBar(
                "Smart Tracker",
                onMenuClick = { scope.launch { drawerState.open() } },
                onQuoteClick = {})
        }, bottomBar = { AppBottomBar(navController) }) { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        QuoteCard(staticQuote)
        TodayActivitySection()
        TodayHabitsSection()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    SmartTaskTrackerTheme() {
        HomeScreen(null)
    }
}
