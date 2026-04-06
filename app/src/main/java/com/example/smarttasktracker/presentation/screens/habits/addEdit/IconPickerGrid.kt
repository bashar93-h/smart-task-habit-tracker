package com.example.smarttasktracker.presentation.screens.habits.addEdit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.presentation.utils.habitIcons
import compose.icons.FeatherIcons
import compose.icons.feathericons.Droplet

@Composable
fun IconPickerGrid(selectedIcon: ImageVector, onIconSelected: (ImageVector) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
    ) {
        items(habitIcons) { icon ->
            val isSelected = icon == selectedIcon
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    .clickable { onIconSelected(icon) }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.6f
                    ), modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun IconPickerGridPreview() =
    IconPickerGrid(selectedIcon = FeatherIcons.Droplet, onIconSelected = {})