package com.example.smarttasktracker.presentation.screens.habits.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.utils.HabitWeeklyDone
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.screens.habits.HabitsViewModel
import com.example.smarttasktracker.presentation.screens.habits.addEdit.AddEditHabitSheet
import com.example.smarttasktracker.presentation.screens.habits.lists.components.HabitCard
import com.example.smarttasktracker.presentation.screens.habits.lists.components.HabitSummaryBar
import com.example.smarttasktracker.presentation.screens.habits.lists.components.WeeklyStrip
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Plus
import java.time.LocalDate

@Composable
fun HabitsScreen(navController: NavController?, viewModel: HabitsViewModel = hiltViewModel()) {

    var showAddSheet by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var habitToEdit by remember { mutableStateOf<HabitItem?>(null) }

    val habits = viewModel.habits.collectAsState().value

    if (showAddSheet) {
        AddEditHabitSheet(
            habitToEdit = null,
            onDismiss = { showAddSheet = false },
            onSave = { newHabit ->
                viewModel.addHabit(newHabit)
                showAddSheet = false
            })
    }

    if (habitToEdit != null) {
        AddEditHabitSheet(
            habitToEdit = habitToEdit,
            onDismiss = { habitToEdit = null },
            onSave = { newHabit ->
                viewModel.updateHabit(newHabit)
                habitToEdit = null
            })
    }

    Scaffold(topBar = {
        AppTopBar("Habits")
    }, bottomBar = { AppBottomBar(navController) }, floatingActionButton = {
        FloatingActionButton(
            onClick = { showAddSheet = true },
            modifier = Modifier.padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
        ) {
            Icon(
                imageVector = FeatherIcons.Plus,
                contentDescription = "Add Habit"
            )
        }
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 80.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    WeeklyStrip(
                        selectedDate = selectedDate,
                        onDateSelected = { selectedDate = it },
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                item(span = { GridItemSpan(maxLineSpan) }) {
                    HabitSummaryBar(habits = habits)
                }
                items(
                    items = habits,
                    key = { it.id }
                ) { habit ->
                    val index = habits.indexOfFirst { it.id == habit.id }
                    HabitCard(
                        habit = habit,
                        onToggle = {
                            if (index != -1) {
                                val updatedHabit = habit.copy(isDone = !habit.isDone)
                                val withWeek = HabitWeeklyDone.applyForToday(
                                    habit = updatedHabit,
                                    doneForToday = HabitWeeklyDone.habitDoneForToday(updatedHabit),
                                    today = LocalDate.now()
                                )
                                viewModel.updateHabit(withWeek)
                            }
                        },
                        onIncrement = {
                            if (index != -1 && habit.currentCount < habit.targetCount) {
                                val newCount = habit.currentCount + 1
                                val updatedHabit = habit.copy(
                                    currentCount = newCount,
                                    isDone = newCount >= habit.targetCount
                                )
                                val withWeek = HabitWeeklyDone.applyForToday(
                                    habit = updatedHabit,
                                    doneForToday = HabitWeeklyDone.habitDoneForToday(updatedHabit),
                                    today = LocalDate.now()
                                )
                                viewModel.updateHabit(withWeek)
                            }
                        },
                        onEdit = { habitToEdit = habit },
                        onDelete = { viewModel.deleteHabit(habit) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HabitsScreenPreview() {
    SmartTaskTrackerTheme {
//        HabitsScreen()
    }
}
