package com.example.smarttasktracker.domain.model

import java.time.LocalDate

data class Quote(val id: Int, val text: String, val author: String)
data class SavedQuote(val id: Int, val text: String, val author: String, val dateSaved: LocalDate)
