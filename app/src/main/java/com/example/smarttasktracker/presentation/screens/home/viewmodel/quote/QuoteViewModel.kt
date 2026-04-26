package com.example.smarttasktracker.presentation.screens.home.viewmodel.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttasktracker.domain.model.Quote
import com.example.smarttasktracker.domain.model.SavedQuote
import com.example.smarttasktracker.domain.usecase.quotes.QuoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(private val useCases: QuoteUseCases) :
    ViewModel() {
    private val _state = MutableStateFlow(QuoteState())
    val state: StateFlow<QuoteState> = _state

    private val _quotes = MutableStateFlow<List<SavedQuote>>(emptyList())
    val quotes = _quotes.asStateFlow()

    init {
        getRandomQuote()
        loadQuotes()
    }

    fun loadQuotes() {
        viewModelScope.launch {
            useCases.getQuotes().collect { quotesList ->
                _quotes.value = quotesList
            }
        }
    }

    fun getRandomQuote() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoadingQuote = true)

            try {
                val quote = useCases.getRandomQuote()
                val imageUrl = "https://picsum.photos/400/300?random=${System.currentTimeMillis()}"
                _state.value = _state.value.copy(
                    isLoadingQuote = false,
                    quote = quote,
                    imageUrl = imageUrl,
                    quoteError = ""
                )
            } catch (e: Exception) {
                _state.value =
                    _state.value.copy(
                        isLoadingQuote = false,
                        quoteError = e.message ?: "",
                        quote = null
                    )
            }

        }
    }

    fun getQuoteById(id: Int) {
        viewModelScope.launch {
            useCases.getQuoteById(id)
        }
    }

    fun addQuote(quote: Quote) {
        viewModelScope.launch {
            useCases.insertQuote(quote)
        }
    }

    fun deleteQuote(savedQuote: SavedQuote) {
        viewModelScope.launch {
            useCases.deleteQuote(savedQuote)
        }
    }
}