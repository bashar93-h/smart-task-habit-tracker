package com.example.smarttasktracker.data.datasources.preferences

import com.example.smarttasktracker.domain.model.UserPreferences
import com.example.smarttasktracker.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(private val dataSource: UserPreferencesDataSource) :
    PreferencesRepository {
    override fun getUserPreferences(): Flow<UserPreferences> =
        dataSource.getUserPreferences()

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        dataSource.setDarkMode(isDarkMode)
    }

    override suspend fun setProfileImageUri(uri: String) {
        dataSource.setProfileImageUri(uri)
    }

    override suspend fun setUserName(name: String) {
        dataSource.setUserName(name)
    }
}