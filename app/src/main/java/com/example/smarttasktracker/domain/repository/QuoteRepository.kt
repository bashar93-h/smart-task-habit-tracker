package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.domain.model.Quote
import com.example.smarttasktracker.domain.model.SavedQuote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getRandomQuote(): Quote

    fun getQuotes(): Flow<List<SavedQuote>>

    suspend fun getQuoteById(id: Int): SavedQuote

    suspend fun insertQuote(savedQuote: SavedQuote)

    suspend fun deleteQuote(savedQuote: SavedQuote)
}