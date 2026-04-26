package com.example.smarttasktracker.data.datasources.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.smarttasktracker.domain.model.UserPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDataSource @Inject constructor(@ApplicationContext private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    companion object {
        val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        val KEY_USER_NAME = stringPreferencesKey("user_name")
        val KEY_PROFILE_IMAGE_URI = stringPreferencesKey("profile_image_uri")
    }

    fun getUserPreferences(): Flow<UserPreferences> =
        context.dataStore.data.map { prefs ->
            UserPreferences(
                isDarkMode = prefs[KEY_DARK_MODE] ?: false,
                userName = prefs[KEY_USER_NAME] ?: "Guest",
                profileImageUri = prefs[KEY_PROFILE_IMAGE_URI] ?: ""
            )
        }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DARK_MODE] = isDarkMode
        }
    }

    suspend fun setUserName(name: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USER_NAME] = name
        }
    }

    suspend fun setProfileImageUri(uri: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_PROFILE_IMAGE_URI] = uri
        }
    }
}