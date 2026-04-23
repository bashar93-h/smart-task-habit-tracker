package com.example.smarttasktracker.domain.usecase.quotes

import com.example.smarttasktracker.domain.model.Quote
import com.example.smarttasktracker.domain.model.SavedQuote
import com.example.smarttasktracker.domain.repository.QuoteRepository
import com.example.smarttasktracker.domain.usecase.habits.DeleteHabit
import com.example.smarttasktracker.domain.usecase.habits.GetAllHabits
import com.example.smarttasktracker.domain.usecase.habits.GetHabitById
import com.example.smarttasktracker.domain.usecase.habits.InsertHabit
import com.example.smarttasktracker.domain.usecase.habits.UpdateHabit
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke(): Quote =
        quoteRepository.getRandomQuote()
}

class GetQuotesUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke(): Flow<List<SavedQuote>> =
        quoteRepository.getQuotes()
}

class GetQuoteByIdUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke(id: Int): SavedQuote =
        quoteRepository.getQuoteById(id)
}

class InsertQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke(quote: Quote) =
        quoteRepository.insertQuote(
            SavedQuote(
                id = quote.id,
                text = quote.text,
                author = quote.author,
                dateSaved = LocalDate.now()
            )
        )
}


class DeleteQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    suspend operator fun invoke(savedQuote: SavedQuote) =
        quoteRepository.deleteQuote(savedQuote)
}

data class QuoteUseCases @Inject constructor(
    val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    val getQuotesUseCase: GetQuotesUseCase,
    val getQuoteByIdUseCase: GetQuoteByIdUseCase,
    val insertQuoteUseCase: InsertQuoteUseCase,
    val deleteQuoteUseCase: DeleteQuoteUseCase,
)