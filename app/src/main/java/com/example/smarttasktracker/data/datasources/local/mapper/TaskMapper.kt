package com.example.smarttasktracker.data.datasources.local.mapper

import com.example.smarttasktracker.data.datasources.local.entity.TaskEntity
import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem
import java.time.LocalDate

fun TaskEntity.toDomain(): TaskItem {
    return TaskItem(
        id = id,
        title = title,
        description = description,
        time = time,
        category = category,
        isCompleted = isCompleted,
        priority = Priority.valueOf(priority),
        date = LocalDate.parse(date),
        notes = notes,
        reminder = reminder
    )
}

fun TaskItem.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        time = time,
        category = category,
        isCompleted = isCompleted,
        priority = priority.name,
        date = date.toString(),
        notes = notes,
        reminder = reminder
    )
}