package com.example.smarttasktracker.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun FormSectionLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.5.sp
    )
}

@Preview
@Composable
fun FormSectionLabelPreview() {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        FormSectionLabel("Title")
    }
}