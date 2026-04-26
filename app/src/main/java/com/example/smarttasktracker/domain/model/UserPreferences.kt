package com.example.smarttasktracker.domain.model

data class UserPreferences(
    val isDarkMode: Boolean = false,
    val userName: String = "Guest",
    val profileImageUri : String = ""
)
