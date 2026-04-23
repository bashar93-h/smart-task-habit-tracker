package com.example.smarttasktracker.data.datasources.local.database

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.smarttasktracker.data.datasources.local.dao.HabitDao
import com.example.smarttasktracker.data.datasources.local.dao.QuoteDao
import com.example.smarttasktracker.data.datasources.local.dao.TaskDao
import com.example.smarttasktracker.data.datasources.local.entity.HabitEntity
import com.example.smarttasktracker.data.datasources.local.entity.QuoteEntity
import com.example.smarttasktracker.data.datasources.local.entity.TaskEntity

@Database(entities = [TaskEntity::class, HabitEntity::class, QuoteEntity::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun habitDao(): HabitDao
    abstract fun quoteDao(): QuoteDao
}


val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE habits ADD COLUMN weeklyWeekStartEpochDay INTEGER NOT NULL DEFAULT 0"
        )
    }
}