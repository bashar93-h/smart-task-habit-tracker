package com.example.smarttasktracker.presentation.screens.tasks.addEdit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.presentation.utils.color
import com.example.smarttasktracker.presentation.utils.label

@Composable
fun PrioritySelector(selected: Priority, onSelected: (Priority) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Priority.entries.forEach { priority ->
            val isSelected = selected == priority
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isSelected) priority.color()
                        else priority.color().copy(alpha = 0.1f)
                    )
                    .clickable { onSelected(priority) }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = priority.label(),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = if (isSelected) FontWeight.Bold
                    else FontWeight.Normal,
                    color = if (isSelected) Color.White else priority.color()
                )
            }
        }
    }
}

@Preview
@Composable
fun PrioritySelectorPreview() {
    PrioritySelector(selected = Priority.HIGH, onSelected = {})
}