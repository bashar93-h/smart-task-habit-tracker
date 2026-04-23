package com.example.smarttasktracker.domain.usecase.quotes

import com.example.smarttasktracker.domain.model.Quote
import com.example.smarttasktracker.domain.model.SavedQuote
import com.example.smarttasktracker.domain.repository.QuoteRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class QuoteUseCasesTest {
    private val repository: QuoteRepository = mockk()
    private lateinit var getRandomQuote: GetRandomQuote
    private lateinit var getQuotes: GetQuotes
    private lateinit var getQuoteById: GetQuoteById
    private lateinit var insertQuote: InsertQuote
    private lateinit var deleteQuote: DeleteQuote

    @Before
    fun setup() {
        getRandomQuote = GetRandomQuote(repository)
        getQuotes = GetQuotes(repository)
        getQuoteById = GetQuoteById(repository)
        insertQuote = InsertQuote(repository)
        deleteQuote = DeleteQuote(repository)
    }

    @Test
    fun `getRandomQuote should return random quote from repository`() = runTest {
        val quote = Quote(
            id = "1",
            author = "Author",
            text = "Quote",
        )

        coEvery { repository.getRandomQuote() } returns quote
        val result = getRandomQuote()
        assertEquals(quote, result)
    }

    @Test
    fun `getQuotes should return mapped quotes from repository`() = runTest {
        val quotes = listOf(
            SavedQuote(
                id = "1",
                author = "Author",
                text = "Quote",
                dateSaved = LocalDate.now()
            )
        )
        coEvery { repository.getQuotes() } returns flowOf(quotes)
        val result = getQuotes().first()
        assertEquals(1, result.size)
        assertEquals("Quote", result[0].text)
        assertEquals("Author", result[0].author)
        assertEquals(LocalDate.now(), result[0].dateSaved)

    }

    @Test
    fun `getGetById should return mapped quote from repository`() = runTest {
        val quote = SavedQuote(
            id = "1",
            author = "Author",
            text = "Quote",
            dateSaved = LocalDate.now()
        )

        coEvery { repository.getQuoteById(1) } returns quote
        val result = getQuoteById(1)
        assertEquals(quote, result)
    }

    @Test
    fun `insertQuote should call repository with mapped quote`() = runTest {
        val quote = Quote(
            id = "1",
            author = "Author",
            text = "Quote",
        )

        coEvery { repository.insertQuote(any()) } just Runs
        insertQuote(quote)
        coVerify {
            repository.insertQuote(
                SavedQuote(
                    id = "1",
                    author = "Author",
                    text = "Quote",
                    dateSaved = LocalDate.now()
                )
            )
        }
    }

    @Test
    fun `deleteQuote should call repository`() = runTest {
        val quote = SavedQuote(
            id = "1",
            author = "Author",
            text = "Quote",
            dateSaved = LocalDate.now()
        )

        coEvery { repository.deleteQuote(any()) } just Runs
        deleteQuote(quote)
        coVerify { repository.deleteQuote(quote) }
    }
}