package com.example.smarttasktracker.data.datasources.local.database

import androidx.room.*
import com.example.smarttasktracker.data.datasources.local.dao.HabitDao
import com.example.smarttasktracker.data.datasources.local.dao.TaskDao
import com.example.smarttasktracker.data.datasources.local.entity.HabitEntity
import com.example.smarttasktracker.data.datasources.local.entity.TaskEntity

@Database(entities = [TaskEntity::class, HabitEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun habitDao(): HabitDao
}