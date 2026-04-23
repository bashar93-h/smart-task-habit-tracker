package com.example.smarttasktracker.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smarttasktracker.data.datasources.local.entity.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT * from quotes")
    fun getQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * from quotes where id = :id LIMIT 1")
    suspend fun getQuoteById(id: Int): QuoteEntity

    @Delete
    suspend fun deleteQuote(quoteEntity: QuoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quoteEntity: QuoteEntity)
}