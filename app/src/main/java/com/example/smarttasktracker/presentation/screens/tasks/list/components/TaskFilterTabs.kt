package com.example.smarttasktracker.presentation.screens.tasks.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.domain.model.TaskFilter
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme

@Composable
fun TaskFilterTabs(
    selectedFilter: TaskFilter,
    onFilterSelected: (TaskFilter) -> Unit,
    taskCounts: Map<TaskFilter, Int>,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = TaskFilter.entries.indexOf(selectedFilter),
        modifier = modifier,
        edgePadding = 16.dp,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(
                    tabPositions[TaskFilter.entries.indexOf(selectedFilter)]
                ),
                color = MaterialTheme.colorScheme.primary
            )
        },
        divider = {}
    ) {
        TaskFilter.entries.forEach { filter ->
            val isSelected = filter == selectedFilter
            Tab(
                selected = isSelected,
                onClick = { onFilterSelected(filter) },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = filter.label(),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                        // count badge
                        val count = taskCounts[filter] ?: 0
                        if (count > 0) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(
                                        if (isSelected)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                                    )
                                    .padding(horizontal = 6.dp, vertical = 1.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$count",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isSelected) Color.White
                                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

fun TaskFilter.label() = when (this) {
    TaskFilter.ALL -> "All"
    TaskFilter.TODAY -> "Today"
    TaskFilter.UPCOMING -> "Upcoming"
    TaskFilter.COMPLETED -> "Completed"
}

@Preview
@Composable
fun TaskFilterTabsPreview() {
    SmartTaskTrackerTheme {
        TaskFilterTabs(
            selectedFilter = TaskFilter.ALL, onFilterSelected = {}, taskCounts = mapOf(
                TaskFilter.ALL to 10,
                TaskFilter.COMPLETED to 5,
            )
        )
    }
}
