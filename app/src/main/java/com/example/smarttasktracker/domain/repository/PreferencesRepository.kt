package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getUserPreferences(): Flow<UserPreferences>
    suspend fun setDarkMode(isDarkMode: Boolean)
    suspend fun setUserName(name: String)
    suspend fun setProfileImageUri(uri: String)
}