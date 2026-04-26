package com.example.smarttasktracker.presentation.screens.home.viewmodel.userPrefs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarttasktracker.domain.model.UserPreferences
import com.example.smarttasktracker.domain.usecase.preferences.GetUserPreferencesUseCase
import com.example.smarttasktracker.domain.usecase.preferences.SetDarkModeUseCase
import com.example.smarttasktracker.domain.usecase.preferences.SetProfileImageUseCase
import com.example.smarttasktracker.domain.usecase.preferences.SetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserPreferencesViewModel @Inject constructor(
    private val getUserPreferences: GetUserPreferencesUseCase,
    private val setUserName: SetUserNameUseCase,
    private val setDarkMode: SetDarkModeUseCase,
    private val setProfileImage: SetProfileImageUseCase
) : ViewModel() {
    val preferences: StateFlow<UserPreferences> = getUserPreferences().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserPreferences()
    )

    fun toggleDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch { setDarkMode(isDarkMode) }
    }

    fun updateUserName(name: String) {
        viewModelScope.launch { setUserName(name) }
    }

    fun updateProfileImage(uri: String) {
        viewModelScope.launch { setProfileImage(uri) }
    }
}