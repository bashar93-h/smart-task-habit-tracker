package com.example.smarttasktracker.domain.model

import java.time.LocalDate

data class Quote(val id: String, val text: String, val author: String)
data class SavedQuote(
    val id: String,
    val text: String,
    val author: String,
    val dateSaved: LocalDate
)
