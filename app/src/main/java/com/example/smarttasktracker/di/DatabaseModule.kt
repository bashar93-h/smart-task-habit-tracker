package com.example.smarttasktracker.di

import android.content.Context
import androidx.room.Room
import com.example.smarttasktracker.data.datasources.local.dao.TaskDao
import com.example.smarttasktracker.data.datasources.local.database.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideTasksDatabase(@ApplicationContext context: Context): TasksDatabase =
        Room.databaseBuilder(context, TasksDatabase::class.java, "tasks_db")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideTasksDao(tasksDatabase: TasksDatabase): TaskDao =
        tasksDatabase.taskDao()
}