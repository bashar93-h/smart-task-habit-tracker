package com.example.smarttasktracker.data.datasources.local.mapper

import com.example.smarttasktracker.data.datasources.local.entity.HabitEntity
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType

fun HabitEntity.toDomain(): HabitItem {
    return HabitItem(
        id = id,
        title = title,
        category = category,
        icon = IconMapper.toImageVector(icon),
        type = HabitType.valueOf(type),
        targetCount = targetCount,
        currentCount = currentCount,
        unit = unit,
        isDone = isDone,
        streak = streak,
        reminderTime = reminderTime,
        weeklyDone = weeklyDone
    )
}

fun HabitItem.toEntity(): HabitEntity {
    return HabitEntity(
        id = id,
        title = title,
        category = category,
        icon = IconMapper.toIconName(icon),
        type = type.toString(),
        targetCount = targetCount,
        currentCount = currentCount,
        unit = unit,
        isDone = isDone,
        streak = streak,
        reminderTime = reminderTime,
        weeklyDone = weeklyDone
    )
}