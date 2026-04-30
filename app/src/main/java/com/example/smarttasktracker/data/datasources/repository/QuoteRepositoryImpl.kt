package com.example.smarttasktracker.data.datasources.repository

import com.example.smarttasktracker.data.datasources.local.dao.QuoteDao
import com.example.smarttasktracker.data.datasources.local.mapper.toDomain
import com.example.smarttasktracker.data.datasources.local.mapper.toEntity
import com.example.smarttasktracker.data.datasources.remote.QuoteApiServices
import com.example.smarttasktracker.data.datasources.remote.mapper.toDomain
import com.example.smarttasktracker.domain.model.Quote
import com.example.smarttasktracker.domain.model.SavedQuote
import com.example.smarttasktracker.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val api: QuoteApiServices,
    private val quoteDao: QuoteDao
) : QuoteRepository {
    override suspend fun getRandomQuote(): Quote =
        api.getRandomQuote().toDomain()

    override fun getQuotes(): Flow<List<SavedQuote>> =
        quoteDao.getQuotes().map { list -> list.map { it.toDomain() } }

    override suspend fun getQuoteById(id: Int): SavedQuote =
        quoteDao.getQuoteById(id).toDomain()

    override suspend fun insertQuote(savedQuote: SavedQuote) =
        quoteDao.insertQuote(savedQuote.toEntity())

    override suspend fun  deleteQuote(savedQuote: SavedQuote) =
        quoteDao.deleteQuote(savedQuote.toEntity())
}