package com.example.smarttasktracker.presentation.screens.tasks.addEdit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.presentation.components.CategorySelector
import com.example.smarttasktracker.presentation.components.FormSectionLabel
import com.example.smarttasktracker.presentation.mock.mockTasks
import compose.icons.FeatherIcons
import compose.icons.feathericons.Edit2
import compose.icons.feathericons.Plus
import compose.icons.feathericons.X
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskSheet(
    taskToEdit: TaskItem? = null,
    onDismiss: () -> Unit,
    onSave: (TaskItem) -> Unit
) {
    val isEditing = taskToEdit != null
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var title by remember { mutableStateOf(taskToEdit?.title ?: "") }
    var description by remember { mutableStateOf(taskToEdit?.description ?: "") }
    var selectedDate by remember { mutableStateOf(taskToEdit?.date ?: LocalDate.now()) }
    var selectedTime by remember { mutableStateOf(taskToEdit?.time ?: "08:00 AM") }
    var selectedPriority by remember { mutableStateOf(taskToEdit?.priority ?: Priority.MEDIUM) }
    var selectedCategory by remember { mutableStateOf(taskToEdit?.category ?: "Personal") }
    var notes by remember { mutableStateOf(taskToEdit?.notes ?: "") }
    var selectedReminder by remember { mutableStateOf(taskToEdit?.reminder ?: "None") }

    var titleError by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = MaterialTheme.colorScheme.background,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 4.dp)
                    .size(width = 40.dp, height = 4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
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
                        text = if (isEditing) "Edit Task" else "New Task",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = FeatherIcons.X,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onDismiss() }
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
                        placeholder = { Text("e.g Morning Workout") },
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
                    FormSectionLabel("Description")
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = { Text("Add a short description...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        shape = RoundedCornerShape(12.dp),
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Due Date & Time")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(modifier = Modifier.weight(1.6f)) {
                            DatePickerField(
                                selectedDate = selectedDate,
                                onDateSelected = { selectedDate = it }
                            )
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            TimePickerField(
                                selectedTime = selectedTime,
                                onTimeSelected = { selectedTime = it }
                            )
                        }
                    }
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Priority")
                    PrioritySelector(
                        selected = selectedPriority,
                        onSelected = { selectedPriority = it }
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Category")
                    CategorySelector(
                        selected = selectedCategory,
                        onSelected = { selectedCategory = it }
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Reminder")
                    ReminderSelector(
                        selected = selectedReminder,
                        onSelected = { selectedReminder = it }
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    FormSectionLabel("Notes")
                    OutlinedTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        placeholder = { Text("Any extra notes...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp),
                        shape = RoundedCornerShape(12.dp),
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )
                }
            }
            item {
                Button(
                    onClick = {
                        if (title.isBlank()) {
                            titleError = true
                            return@Button
                        }
                        onSave(
                            TaskItem(
                                id = taskToEdit?.id ?: java.util.UUID.randomUUID().hashCode(),
                                title = title.trim(),
                                description = description.trim(),
                                time = selectedTime,
                                category = selectedCategory,
                                isCompleted = taskToEdit?.isCompleted ?: false,
                                priority = selectedPriority,
                                date = selectedDate,
                                notes = notes.trim(),
                                reminder = selectedReminder
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
                            text = if (isEditing) "Save Changes" else "Add Task",
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
fun AddEditTaskSheetPreview() {
    AddEditTaskSheet(onDismiss = {}, onSave = {})
}