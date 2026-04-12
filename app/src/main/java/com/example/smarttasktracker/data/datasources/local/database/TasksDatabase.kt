package com.example.smarttasktracker.data.datasources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smarttasktracker.data.datasources.local.dao.TaskDao
import com.example.smarttasktracker.data.datasources.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}