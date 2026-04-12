package com.example.smarttasktracker.presentation.screens.tasks.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.mock.mockTasks
import com.example.smarttasktracker.presentation.screens.tasks.TasksViewModel
import com.example.smarttasktracker.presentation.screens.tasks.addEdit.AddEditTaskSheet
import com.example.smarttasktracker.presentation.screens.tasks.details.components.DetailInfoRow
import com.example.smarttasktracker.presentation.screens.tasks.components.DeleteTaskDialog
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import com.example.smarttasktracker.presentation.utils.color
import com.example.smarttasktracker.presentation.utils.label
import compose.icons.FeatherIcons
import compose.icons.feathericons.Bell
import compose.icons.feathericons.Calendar
import compose.icons.feathericons.Check
import compose.icons.feathericons.Clock
import compose.icons.feathericons.Edit2
import compose.icons.feathericons.FileText
import compose.icons.feathericons.Tag
import compose.icons.feathericons.Trash2
import compose.icons.feathericons.X
import java.time.format.DateTimeFormatter

@Composable
fun TaskDetailsScreen(
    taskId: Int,
    navController: NavController?,
    viewModel: TasksViewModel = hiltViewModel()
) {
    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }
    val task by viewModel.selectedTask.collectAsState()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditSheet by remember { mutableStateOf(false) }

    if (showEditSheet && task != null) {
        AddEditTaskSheet(
            taskToEdit = task,
            onDismiss = { showEditSheet = false },
            onSave = { updatedTask ->
                viewModel.updateTask(updatedTask)
                showEditSheet = false
            })
    }

    Scaffold(topBar = {
        AppTopBar("Task Details", onBackClick = { navController?.popBackStack() }, actions = {
            IconButton(onClick = { showEditSheet = true }, enabled = task != null) {
                Icon(
                    imageVector = FeatherIcons.Edit2,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(onClick = { showDeleteDialog = true }, enabled = task != null) {
                Icon(
                    imageVector = FeatherIcons.Trash2,
                    contentDescription = "Delete",
                    tint = Color(0xFFEF5350)
                )
            }
        })
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (task == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                val currentTask = task!!
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(currentTask.priority.color().copy(alpha = 0.12f))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = currentTask.priority.label() + " Priority",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = currentTask.priority.color(),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = currentTask.title,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = if (currentTask.isCompleted) 0.4f else 1f
                                ),
                                textDecoration = if (currentTask.isCompleted) TextDecoration.LineThrough else null
                            )
                            if (currentTask.description.isNotBlank()) {
                                Text(
                                    text = currentTask.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Details",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            DetailInfoRow(
                                icon = FeatherIcons.Calendar,
                                label = "Due Date",
                                value = currentTask.date.format(
                                    DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy")
                                )
                            )
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outlineVariant.copy(
                                    alpha = 0.5f
                                )
                            )
                            DetailInfoRow(
                                icon = FeatherIcons.Clock,
                                label = "Time",
                                value = currentTask.time
                            )
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outlineVariant.copy(
                                    alpha = 0.5f
                                )
                            )
                            DetailInfoRow(
                                icon = FeatherIcons.Tag,
                                label = "Category",
                                value = currentTask.category
                            )
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outlineVariant.copy(
                                    alpha = 0.5f
                                )
                            )
                            DetailInfoRow(
                                icon = FeatherIcons.Bell,
                                label = "Reminder",
                                value = currentTask.reminder
                            )
                        }
                    }
                }
                item {
                    if (currentTask.notes.isNotBlank()) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = FeatherIcons.FileText,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Notes",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text(
                                    text = currentTask.notes,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                    lineHeight = 22.sp
                                )
                            }

                        }
                    }
                }
                item {
                    Button(
                        onClick = {
                            val updatedTask =
                                currentTask.copy(isCompleted = !currentTask.isCompleted)
                            viewModel.updateTask(updatedTask)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentTask.isCompleted) MaterialTheme.colorScheme.surfaceVariant
                            else
                                MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = if (currentTask.isCompleted) FeatherIcons.X else FeatherIcons.Check,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = if (currentTask.isCompleted) "Mark as Incomplete" else "Mark as complete",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                }
            }
        }
    }
    if (showDeleteDialog && task != null) {
        DeleteTaskDialog(
            onConfirm = {
                viewModel.deleteTask(task!!)
                showDeleteDialog = false
                navController?.popBackStack()
            },
            onDismiss = { showDeleteDialog = false })
    }
}

@Preview
@Composable
fun TaskDetailsScreenPreview() {
    SmartTaskTrackerTheme {
        TaskDetailsScreen(
            taskId = mockTasks[9].id,
            null
        )
    }
}
