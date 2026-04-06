package com.example.smarttasktracker.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.presentation.utils.categoryOptions

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySelector(selected: String, onSelected: (String) -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy((8.dp)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categoryOptions.forEach { category ->
            val isSelected = selected == category
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                    )
                    .clickable { onSelected(category) }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isSelected) Color.White
                    else MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.6f
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun CategorySelectorPreview() {
    CategorySelector(selected = "Education", onSelected = {})
}