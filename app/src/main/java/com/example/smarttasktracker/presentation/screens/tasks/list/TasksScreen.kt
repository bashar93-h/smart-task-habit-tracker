package com.example.smarttasktracker.presentation.screens.tasks.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smarttasktracker.domain.model.TaskFilter
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.presentation.components.AppBottomBar
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.navigation.Screen
import com.example.smarttasktracker.presentation.screens.tasks.list.components.GroupedTaskList
import com.example.smarttasktracker.presentation.screens.tasks.list.components.TaskFilterTabs
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Plus
import java.time.LocalDate

@Composable
fun TasksScreen(tasks: SnapshotStateList<TaskItem>, navController: NavController?, modifier: Modifier = Modifier) {

    var selectedFilter by remember { mutableStateOf(TaskFilter.ALL) }

    val filteredTasks = remember(selectedFilter, tasks.toList()) {
        val today = LocalDate.now()
        when (selectedFilter) {
            TaskFilter.ALL -> tasks.filter { !it.isCompleted }
            TaskFilter.TODAY -> tasks.filter { it.date == today && !it.isCompleted }
            TaskFilter.UPCOMING -> tasks.filter { it.date.isAfter(today) && !it.isCompleted }
            TaskFilter.COMPLETED -> tasks.filter { it.isCompleted }
        }
    }

    val taskCounts = remember(tasks.toList()) {
        val today = LocalDate.now()
        mapOf(
            TaskFilter.ALL to tasks.count { !it.isCompleted },
            TaskFilter.TODAY to tasks.count { it.date == today && !it.isCompleted },
            TaskFilter.UPCOMING to tasks.count { it.date.isAfter(today) && !it.isCompleted },
            TaskFilter.COMPLETED to tasks.count { it.isCompleted }
        )
    }
    Scaffold(topBar = {
        AppTopBar("Tasks")
    }, bottomBar = { AppBottomBar(navController) }, floatingActionButton = {
        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
        ) {
            Icon(
                imageVector = FeatherIcons.Plus,
                contentDescription = "Add Task"
            )
        }
    }) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TaskFilterTabs(
                    selectedFilter = selectedFilter,
                    onFilterSelected = { selectedFilter = it },
                    taskCounts = taskCounts
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                GroupedTaskList(
                    filteredTasks,
                    onCheckedChange = { id ->
                        val index = tasks.indexOfFirst { it.id == id }
                        tasks[index] = tasks[index].copy(isCompleted = !tasks[index].isCompleted)
                    },
                    onTaskClick = { id ->
                        navController?.navigate(
                            Screen.TaskDetails.createRoute(
                                id
                            )
                        )
                    },
                    onDelete = { id -> tasks.removeAll { it.id == id } })
            }

        }

    }
}


@Preview
@Composable
fun TasksScreenPreview() {
    SmartTaskTrackerTheme() {
//        TasksScreen(tasks = em,null)
    }
}
