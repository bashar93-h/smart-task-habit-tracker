package com.example.smarttasktracker.domain.usecase.preferences

import com.example.smarttasktracker.domain.model.UserPreferences
import com.example.smarttasktracker.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUserPreferencesUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    operator fun invoke(): Flow<UserPreferences> = repository.getUserPreferences()
}

class SetDarkModeUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(isDarkMode: Boolean) =
        repository.setDarkMode(isDarkMode)
}

class SetUserNameUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(name: String) =
        repository.setUserName(name)
}

class SetProfileImageUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(uri: String) =
        repository.setProfileImageUri(uri)
}
