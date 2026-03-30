package com.example.smarttasktracker.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppDrawer
import com.example.smarttasktracker.presentation.mock.staticQuote
import com.example.smarttasktracker.presentation.screens.home.components.ExpandableFab
import com.example.smarttasktracker.presentation.screens.home.components.HomeTopBar
import com.example.smarttasktracker.presentation.screens.home.components.MotivationBottomSheet
import com.example.smarttasktracker.presentation.screens.home.components.QuoteCard
import com.example.smarttasktracker.presentation.screens.home.components.TodayActivitySection
import com.example.smarttasktracker.presentation.screens.home.components.TodayHabitsSection
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController?) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showMotivationSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (showMotivationSheet) {
        MotivationBottomSheet(onDismiss = { showMotivationSheet = false }, onSaveToFavorites = {})
    }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        AppDrawer(onNavigate = { route ->
            navController?.navigate(route)
            scope.launch { drawerState.close() }
        }, onClose = {})
    }) {
        Scaffold(topBar = {
            HomeTopBar(
                "Smart Tracker",
                onMenuClick = { scope.launch { drawerState.open() } },
                onQuoteClick = { showMotivationSheet = true })
        }, bottomBar = { AppBottomBar(navController) }, floatingActionButton = {
            ExpandableFab(onAddHabit = {}, onAddTask = {})
        }) { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                MainContent(navController)
            }
        }
    }
}

@Composable
fun MainContent(navController: NavController?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            QuoteCard(staticQuote)
        }
        item {
            TodayActivitySection(navController = navController)
        }
        item {
            TodayHabitsSection(navController = navController)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    SmartTaskTrackerTheme{
        HomeScreen(null)
    }
}
