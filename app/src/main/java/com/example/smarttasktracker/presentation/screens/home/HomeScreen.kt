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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppDrawer
import com.example.smarttasktracker.presentation.mock.staticQuote
import com.example.smarttasktracker.presentation.screens.habits.HabitsViewModel
import com.example.smarttasktracker.presentation.screens.habits.addEdit.AddEditHabitSheet
import com.example.smarttasktracker.presentation.screens.home.components.ExpandableFab
import com.example.smarttasktracker.presentation.screens.home.components.HomeTopBar
import com.example.smarttasktracker.presentation.screens.home.components.MotivationBottomSheet
import com.example.smarttasktracker.presentation.screens.home.components.QuoteCard
import com.example.smarttasktracker.presentation.screens.home.components.TodayActivitySection
import com.example.smarttasktracker.presentation.screens.home.components.TodayHabitsSection
import com.example.smarttasktracker.presentation.screens.tasks.TasksViewModel
import com.example.smarttasktracker.presentation.screens.tasks.addEdit.AddEditTaskSheet
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController?,
    tasksViewModel: TasksViewModel = hiltViewModel(),
    habitsViewModel: HabitsViewModel = hiltViewModel()
) {
    val tasks = tasksViewModel.tasks.collectAsState().value
    val habits = habitsViewModel.habits.collectAsState().value

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showMotivationSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    var showAddHabitSheet by remember { mutableStateOf(false) }
    var showAddTaskSheet by remember { mutableStateOf(false) }

    if (showMotivationSheet) {
        MotivationBottomSheet(onDismiss = { showMotivationSheet = false })
    }

    if (showAddTaskSheet) {
        AddEditTaskSheet(
            taskToEdit = null,
            onDismiss = { showAddTaskSheet = false },
            onSave = { newTask ->
                tasksViewModel.addTask(newTask)
                showAddTaskSheet = false
            })
    }

    if (showAddHabitSheet) {
        AddEditHabitSheet(
            habitToEdit = null,
            onDismiss = { showAddHabitSheet = false },
            onSave = { newHabit ->
                habitsViewModel.addHabit(newHabit)
                showAddHabitSheet = false
            })
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
            ExpandableFab(
                onAddHabit = { showAddHabitSheet = true },
                onAddTask = { showAddTaskSheet = true })
        }) { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            ) {
                MainContent(tasks, habits, navController)
            }
        }
    }
}

@Composable
fun MainContent(tasks: List<TaskItem>, habits: List<HabitItem>, navController: NavController?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            QuoteCard()
        }
        item {
            TodayActivitySection(tasks = tasks, navController = navController)
        }
        item {
            TodayHabitsSection(habits = habits, navController = navController)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    SmartTaskTrackerTheme {
        HomeScreen(null)
    }
}
