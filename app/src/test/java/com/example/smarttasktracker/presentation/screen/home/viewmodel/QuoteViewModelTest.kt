package com.example.smarttasktracker.presentation.screen.home.viewmodel

import com.example.smarttasktracker.domain.model.Quote
import com.example.smarttasktracker.domain.model.SavedQuote
import com.example.smarttasktracker.domain.usecase.quotes.QuoteUseCases
import com.example.smarttasktracker.presentation.screens.home.viewmodel.quote.QuoteViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class QuoteViewModelTest {
    private lateinit var useCases: QuoteUseCases
    private lateinit var viewModel: QuoteViewModel

    val dispatcher = StandardTestDispatcher()

    val quote = Quote(
        id = "1",
        text = "Quote",
        author = "Author"
    )

    val savedQuote = SavedQuote(
        id = "1",
        text = "Quote",
        author = "Author",
        dateSaved = LocalDate.now()
    )


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        useCases = mockk(relaxed = true)

        coEvery { useCases.getRandomQuote() } returns quote
        coEvery { useCases.getQuotes() } returns flowOf(emptyList())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    // build viewmodel and let all init coroutines finish
    private fun buildViewModel(): QuoteViewModel {
        val vm = QuoteViewModel(useCases)
        dispatcher.scheduler.advanceUntilIdle()
        return vm
    }

    @Test
    fun `init calls getRandomQuote and loadQuotes`() = runTest {
        buildViewModel()
        coVerify(exactly = 1) { useCases.getRandomQuote() }
        coVerify(exactly = 1) { useCases.getQuotes() }
    }


    @Test
    fun `getRandomQuote sets isLoadingQuote to true then false on success`() = runTest {
        val vm = buildViewModel()
        coEvery { useCases.getRandomQuote() } returns quote

        vm.getRandomQuote()
        dispatcher.scheduler.advanceUntilIdle()

        val state = vm.state.value
        assertFalse(state.isLoadingQuote)
    }

    @Test
    fun `getRandomQuote updates state with quote on success`() {
        val vm = buildViewModel()
        val state = vm.state.value

        assertEquals(quote, state.quote)
        assertFalse(state.isLoadingQuote)
        assertEquals("", state.quoteError)
    }

    @Test
    fun `getRandomQuote sets a non-empty imageUrl on success`() {
        val vm = buildViewModel()

        assertTrue(vm.state.value.imageUrl.isNotEmpty())
        assertTrue(vm.state.value.imageUrl.startsWith("https://picsum.photos/"))
    }

    @Test
    fun `getRandomQuote sets quoteError on exception`() {
        coEvery { useCases.getRandomQuote() } throws RuntimeException("Network error")
        val vm = buildViewModel()

        val state = vm.state.value

        assertEquals("Network error", state.quoteError)
        assertNull(state.quote)
    }

    @Test
    fun `getRandomQuote clears previous error on successful retry`() {
        coEvery { useCases.getRandomQuote() } throws RuntimeException("fail")
        val vm = buildViewModel()
        assertEquals("fail", vm.state.value.quoteError)

        coEvery { useCases.getRandomQuote() } returns quote
        vm.getRandomQuote()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("", vm.state.value.quoteError)
        assertEquals(quote, vm.state.value.quote)
    }

    @Test
    fun `loadQuotes emits saved quotes from use case`() {
        val savedList = listOf(savedQuote)
        coEvery { useCases.getQuotes() } returns flowOf(savedList)
        val vm = buildViewModel()

        assertEquals(savedList, vm.quotes.value)
    }

    @Test
    fun `loadQuotes emits multiple emissions in order`() {
        val first = listOf(savedQuote)
        val second = listOf(savedQuote, savedQuote.copy(id = "2"))
        coEvery { useCases.getQuotes() } returns flowOf(first, second)
        val vm = buildViewModel()

        // the final emission wins
        assertEquals(second, vm.quotes.value)
    }

    @Test
    fun `addQuote delegates to insertQuote use case`() {
        val vm = buildViewModel()
        coEvery { useCases.insertQuote(any()) } just Runs
        vm.addQuote(quote)
        dispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) { useCases.insertQuote(quote) }
    }

    @Test
    fun `deleteQuote delegates to deleteQuote use case`() {
        val vm = buildViewModel()
        coEvery { useCases.deleteQuote(any()) } just Runs
        vm.deleteQuote(savedQuote)
        dispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) { useCases.deleteQuote(savedQuote) }
    }

    @Test
    fun `getQuoteById delegates to use case with correct id`() {
        val vm = buildViewModel()
        coEvery { useCases.getQuoteById(42) } returns savedQuote
        vm.getQuoteById(42)
        dispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = 1) { useCases.getQuoteById(42) }
    }
}

// relaxed = ture -> “means don’t crash if I didn’t define behavior for a function", just return the default value


