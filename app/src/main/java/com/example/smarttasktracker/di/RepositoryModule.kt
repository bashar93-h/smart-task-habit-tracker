package com.example.smarttasktracker.di

import com.example.smarttasktracker.data.datasources.local.repository.TasksRepositoryImpl
import com.example.smarttasktracker.domain.repository.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTasksRepository(tasksRepositoryImpl: TasksRepositoryImpl): TasksRepository
}