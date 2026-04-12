package com.example.smarttasktracker.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smarttasktracker.data.datasources.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * from tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * From tasks where id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Int): TaskEntity

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: TaskEntity)
}