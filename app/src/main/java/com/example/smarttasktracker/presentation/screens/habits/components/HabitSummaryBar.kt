package com.example.smarttasktracker.presentation.screens.habits.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
import com.example.smarttasktracker.presentation.mock.mockHabits

@Composable
fun HabitSummaryBar(habits: List<HabitItem>, modifier: Modifier = Modifier) {
    val total = habits.size
    val done = habits.count { habit ->
        when (habit.type) {
            HabitType.SIMPLE -> habit.isDone
            else ->
                habit.currentCount >= habit.targetCount
        }
    }
    val percentage = if (total > 0) (done * 100) / total else 0
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$total habits today",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = "$percentage% completed",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun HabitSummaryBarPreview() {
    MaterialTheme {
        HabitSummaryBar(habits = mockHabits)
    }
}
