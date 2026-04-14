package com.example.smarttasktracker.di

import android.content.Context
import androidx.room.Room
import com.example.smarttasktracker.data.datasources.local.dao.HabitDao
import com.example.smarttasktracker.data.datasources.local.dao.TaskDao
import com.example.smarttasktracker.data.datasources.local.database.AppDatabase
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
    fun provideTasksDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun provideTasksDao(appDatabase: AppDatabase): TaskDao =
        appDatabase.taskDao()

    @Provides
    @Singleton
    fun provideHabitsDao(appDatabase: AppDatabase): HabitDao =
        appDatabase.habitDao()
}