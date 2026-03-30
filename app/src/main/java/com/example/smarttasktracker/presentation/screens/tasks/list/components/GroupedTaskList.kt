package com.example.smarttasktracker.presentation.screens.tasks.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.presentation.mock.mockTasks
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.CheckCircle
import java.time.LocalDate

@Composable
fun GroupedTaskList(
    tasks: List<TaskItem>,
    onCheckedChange: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onTaskClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()

    fun LocalDate.groupLabel(): String = when {
        this == today -> "Today"
        this == today.plusDays(1) -> "Tomorrow"
        this.isBefore(today.plusWeeks(1)) -> "This Week"
        else -> "Later"
    }

    val grouped = tasks
        .sortedWith(compareBy({ it.date }, { it.time }))
        .groupBy { it.date.groupLabel() }

    val groupOrder = listOf("Today", "Tomorrow", "This Week", "Later")

    if (tasks.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = FeatherIcons.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = "No tasks here!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            }
        }
        return
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        groupOrder.forEach { label ->
            val groupTasks = grouped[label] ?: return@forEach
            item {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
            }
            items(
                items = groupTasks,
                key = { it.id }
            ) { task ->
                TaskItemCard(
                    task = task,
                    onCheckedChange = { onCheckedChange(task.id) },
                    onDelete = { onDelete(task.id) },
                    onClick = { onTaskClick(task.id) }
                )
            }
        }
    }
}


@Preview
@Composable
fun GroupedTaskListPreview() {
    SmartTaskTrackerTheme {
        GroupedTaskList(
            tasks = mockTasks,
            onCheckedChange = { },
            onDelete = {},
            onTaskClick = {})
    }
}
