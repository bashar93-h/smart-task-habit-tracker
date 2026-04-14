package com.example.smarttasktracker.presentation.screens.habits.addEdit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
import com.example.smarttasktracker.presentation.mock.mockHabits
import com.example.smarttasktracker.presentation.components.CategorySelector
import com.example.smarttasktracker.presentation.components.FormSectionLabel
import com.example.smarttasktracker.presentation.screens.tasks.addEdit.TimePickerField
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit2
import compose.icons.feathericons.Plus
import compose.icons.feathericons.Star
import compose.icons.feathericons.X

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditHabitSheet(
    habitToEdit: HabitItem? = null,
    onDismiss: () -> Unit,
    onSave: (HabitItem) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isEditing = habitToEdit != null

    var title by remember { mutableStateOf(habitToEdit?.title ?: "") }
    var selectedIcon by remember { mutableStateOf(habitToEdit?.icon ?: FeatherIcons.Star) }
    var selectedCategory by remember { mutableStateOf(habitToEdit?.category ?: "Health") }
    var selectedType by remember { mutableStateOf(habitToEdit?.type ?: HabitType.SIMPLE) }
    var targetCount by remember { mutableStateOf(habitToEdit?.targetCount?.toString() ?: "1") }
    var reminderTime by remember { mutableStateOf(habitToEdit?.reminderTime ?:"08:00 AM") }
    var unit by remember { mutableStateOf(habitToEdit?.unit ?: "") }

    var titleError by remember { mutableStateOf(false) }
    var targetError by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 4.dp)
                    .size(width = 40.dp, height = 4.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                    .clip(RoundedCornerShape(2.dp))
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isEditing) "Edit Habit" else "New Habit",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = FeatherIcons.X,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onDismiss()
                            }
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Title *")
                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                            titleError = false
                        },
                        placeholder = { Text("e.g Drink Water") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        isError = titleError,
                        supportingText = {
                            if (titleError) Text(
                                "Title is required",
                                color = MaterialTheme.colorScheme.error
                            )
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Icon")
                    IconPickerGrid(
                        selectedIcon = selectedIcon,
                        onIconSelected = { selectedIcon = it })
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Category")
                    CategorySelector(
                        selected = selectedCategory,
                        onSelected = { selectedCategory = it })
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Habit Type")
                    HabitTypeSelector(
                        selectedType = selectedType,
                        onSelected = { selectedType = it })
                }
            }
            item {
                AnimatedVisibility(visible = selectedType != HabitType.SIMPLE) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        FormSectionLabel(if (selectedType == HabitType.TIME) "Duration Target" else "Count Target")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedTextField(
                                value = targetCount,
                                onValueChange = {
                                    if (it.all { c -> c.isDigit() }) {
                                        targetCount = it
                                        targetError = false
                                    }
                                },
                                placeholder = { Text("e.g 8") },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                isError = targetError,
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                                )
                            )
                            OutlinedTextField(
                                value = unit,
                                onValueChange = {
                                    unit = it
                                },
                                placeholder = { Text(if (selectedType == HabitType.TIME) "min" else "glasses") },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                                )
                            )
                        }
                        if (targetError) {
                            Text(
                                text = "Please enter a valid target",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Daily Reminder")
                    TimePickerField(
                        selectedTime = reminderTime,
                        onTimeSelected = { reminderTime = it })
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Checkbox(checked = reminderTime == "No Reminder", onCheckedChange = {
                            reminderTime = if (it) "No Reminder" else "08:00 AM"
                        })
                        Text(
                            text = "No reminder",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
            item {
                Button(
                    onClick = {
                        if (title.isBlank()) {
                            titleError = true
                            return@Button
                        }
                        if (selectedType != HabitType.SIMPLE && targetCount.isBlank()) {
                            targetError = true
                            return@Button
                        }
                        onSave(
                            HabitItem(
                                id = habitToEdit?.id ?: java.util.UUID.randomUUID().hashCode(),
                                title = title.trim(),
                                category = selectedCategory,
                                icon = selectedIcon,
                                type = selectedType,
                                targetCount = if (selectedType != HabitType.SIMPLE) targetCount.toInt() else 1,
                                currentCount = habitToEdit?.currentCount ?: 0,
                                unit = unit.trim(),
                                isDone = habitToEdit?.isDone ?: false,
                                streak = habitToEdit?.streak ?: 0,
                                reminderTime = reminderTime,
                                weeklyDone = habitToEdit?.weeklyDone ?: List(7) { false },
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = if (isEditing) FeatherIcons.Edit2 else FeatherIcons.Plus,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = if (isEditing) "Save Changes" else "Add Habit",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun AddEditHabitSheetPreview() = AddEditHabitSheet(onDismiss = {}, onSave = {})