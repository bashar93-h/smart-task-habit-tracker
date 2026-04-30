package com.example.smarttasktracker.presentation.screens.home.viewmodel.quote

sealed class QuoteEvent {
    data class ShowToast(val message: String) : QuoteEvent()
}