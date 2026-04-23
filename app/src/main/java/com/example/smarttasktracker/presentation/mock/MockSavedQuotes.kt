package com.example.smarttasktracker.presentation.mock

// presentation/mock/MockSavedQuotes.kt
import com.example.smarttasktracker.domain.model.SavedQuote
import java.time.LocalDate

val mockSavedQuotes = listOf(
    SavedQuote(
        id = "1",
        text = "The secret of getting ahead is getting started.",
        author = "Mark Twain",
        dateSaved = LocalDate.now()
    ),
    SavedQuote(
        id = "2",
        text = "It always seems impossible until it's done.",
        author = "Nelson Mandela",
        dateSaved = LocalDate.now().minusDays(1)
    ),
    SavedQuote(
        id = "3",
        text = "Don't watch the clock; do what it does. Keep going.",
        author = "Sam Levenson",
        dateSaved = LocalDate.now().minusDays(2)
    ),
    SavedQuote(
        id = "4",
        text = "Success is not final, failure is not fatal: it is the courage to continue that counts.",
        author = "Winston Churchill",
        dateSaved = LocalDate.now().minusDays(3)
    ),
    SavedQuote(
        id = "5",
        text = "Believe you can and you're halfway there.",
        author = "Theodore Roosevelt",
        dateSaved = LocalDate.now().minusDays(5)
    ),
)