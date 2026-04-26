package com.example.smarttasktracker.presentation.screens.home.viewmodel.quote

import com.example.smarttasktracker.domain.model.Quote

data class QuoteState(
    val isLoadingQuote: Boolean = false,
    val quote: Quote? = null,
    val imageUrl: String = "",
    val quoteError: String = ""
)