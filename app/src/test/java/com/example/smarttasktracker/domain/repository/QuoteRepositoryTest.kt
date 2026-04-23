package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.data.datasources.local.dao.QuoteDao
import com.example.smarttasktracker.data.datasources.local.entity.QuoteEntity
import com.example.smarttasktracker.data.datasources.remote.QuoteApiServices
import com.example.smarttasktracker.data.datasources.remote.dto.QuoteDto
import com.example.smarttasktracker.data.datasources.repository.QuoteRepositoryImpl
import com.example.smarttasktracker.domain.model.SavedQuote
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class QuoteRepositoryImplTest {
    private lateinit var repository: QuoteRepositoryImpl

    private val api: QuoteApiServices = mockk()
    private val quoteDao: QuoteDao = mockk()

    @Before
    fun setup() {
        repository = QuoteRepositoryImpl(api, quoteDao)
    }

    @Test
    fun `getRandomQuote should return random mapped domain quote`() = runTest {
        val quoteDto = QuoteDto(
            id = 1,
            author = "Author",
            quote = "Quote"
        )
        coEvery { api.getRandomQuote() } returns quoteDto
        val result = repository.getRandomQuote()
        assertEquals(quoteDto.id.toString(), result.id)
        assertEquals(quoteDto.author, result.author)
        assertEquals(quoteDto.quote, result.text)
    }

    @Test
    fun `getQuotes should return mapped domain quotes`() = runTest {
        val entities = listOf(
            QuoteEntity(
                id = "1",
                author = "Author",
                text = "Quote",
                dateSaved = LocalDate.now().toString()
            )
        )

        every { quoteDao.getQuotes() } returns flowOf(entities)
        val result = repository.getQuotes().first()
        assertEquals(1, result.size)
        assertEquals("1", result[0].id)
        assertEquals("Quote", result[0].text)
        assertEquals(LocalDate.now(), result[0].dateSaved)
    }

    @Test
    fun `getQuoteById should return mapped domain quote`() = runTest {
        val entity = QuoteEntity(
            id = "1",
            author = "Author",
            text = "Quote",
            dateSaved = LocalDate.now().toString()
        )
        coEvery { quoteDao.getQuoteById(1) } returns entity
        val result = repository.getQuoteById(1)
        assertEquals("1", result.id)
        assertEquals("Author", result.author)
        assertEquals(LocalDate.now(), result.dateSaved)
    }

    @Test
    fun `insertQuote should cal dao with mapped entity`() = runTest {
        val savedQuote = SavedQuote(
            id = "1",
            author = "Author",
            text = "Quote",
            dateSaved = LocalDate.now()
        )
        coEvery { quoteDao.insertQuote(any()) } just Runs
        repository.insertQuote(savedQuote)
        coVerify {
            quoteDao.insertQuote(match {
                it.id == savedQuote.id && it.dateSaved == savedQuote.dateSaved.toString()
            })
        }
    }

    @Test
    fun `deleteQuote should cal dao with mapped entity`() = runTest {
        val savedQuote = SavedQuote(
            id = "1",
            author = "Author",
            text = "Quote",
            dateSaved = LocalDate.now()
        )
        coEvery { quoteDao.deleteQuote(any()) } just Runs
        repository.deleteQuote(savedQuote)
        coVerify {
            quoteDao.deleteQuote(match {
                it.id == savedQuote.id && it.dateSaved == savedQuote.dateSaved.toString()
            })
        }
    }
}