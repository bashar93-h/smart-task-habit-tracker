package com.example.smarttasktracker.data.datasources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val category: String,
    val icon: String,
    val type: String,
    val targetCount: Int = 1,
    val currentCount: Int = 0,
    val unit: String = "",
    val isDone: Boolean = false,
    val streak: Int = 0,
    val reminderTime: String = "No Reminder",
    val weeklyDone: List<Boolean> = List(7) { false }
)