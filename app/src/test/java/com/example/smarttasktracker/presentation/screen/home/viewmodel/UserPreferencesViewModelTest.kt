package com.example.smarttasktracker.presentation.screen.home.viewmodel

import app.cash.turbine.test
import com.example.smarttasktracker.domain.model.UserPreferences
import com.example.smarttasktracker.domain.usecase.preferences.GetUserPreferencesUseCase
import com.example.smarttasktracker.domain.usecase.preferences.SetDarkModeUseCase
import com.example.smarttasktracker.domain.usecase.preferences.SetProfileImageUseCase
import com.example.smarttasktracker.domain.usecase.preferences.SetUserNameUseCase
import com.example.smarttasktracker.presentation.screens.home.viewmodel.userPrefs.UserPreferencesViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class UserPreferencesViewModelTest {
    val testDispatcher = StandardTestDispatcher()

    private lateinit var getUserPreferencesUseCase: GetUserPreferencesUseCase
    private lateinit var setUserNameUseCase: SetUserNameUseCase
    private lateinit var setDarkModeUseCase: SetDarkModeUseCase
    private lateinit var setProfileImageUseCase: SetProfileImageUseCase
    private lateinit var viewModel: UserPreferencesViewModel

    private val defaultPreferences = UserPreferences(
        isDarkMode = false,
        userName = "Guest",
        profileImageUri = ""
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getUserPreferencesUseCase = mockk()
        setUserNameUseCase = mockk()
        setDarkModeUseCase = mockk()
        setProfileImageUseCase = mockk()

        every { getUserPreferencesUseCase() } returns flowOf(defaultPreferences)

        viewModel = UserPreferencesViewModel(
            getUserPreferencesUseCase, setUserNameUseCase, setDarkModeUseCase,
            setProfileImageUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearUp() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `preferences initial value is default UserPreferences`() = runTest {
        advanceUntilIdle()
        assertEquals(defaultPreferences, viewModel.preferences.value)
    }

    @Test
    fun `preferences emits value from GetUserPreferencesUseCase`() = runTest {
        val updatedPreferences = defaultPreferences.copy(
            isDarkMode = true,
            userName = "Alice"
        )
        every { getUserPreferencesUseCase() } returns flowOf(defaultPreferences, updatedPreferences)

        // we recreate the viewmodel so it uses to new mocked behavior
        val newViewModel = UserPreferencesViewModel(
            getUserPreferences = getUserPreferencesUseCase,
            setUserName = setUserNameUseCase,
            setDarkMode = setDarkModeUseCase,
            setProfileImage = setProfileImageUseCase
        )

        // Start collecting this Flow
        newViewModel.preferences.test {
            assertEquals(defaultPreferences, awaitItem())// wait until flow emits the next value
            assertEquals(updatedPreferences, awaitItem())
            // Stop collecting the Flow and ignore anything else
            cancelAndIgnoreRemainingEvents()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleDarkMode true invokes SetDarkModeUseCase with true`() = runTest {
        coEvery { setDarkModeUseCase(any()) } just Runs
        viewModel.toggleDarkMode(true)
        advanceUntilIdle()
        coVerify { setDarkModeUseCase(true) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleDarkMode false invokes SetDarkModeUseCase with false`() = runTest {
        coEvery { setDarkModeUseCase(any()) } just Runs
        viewModel.toggleDarkMode(false)
        advanceUntilIdle()
        coVerify { setDarkModeUseCase(false) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateUserName invokes SetUserNameUseCase with given name`() = runTest {
        coEvery { setUserNameUseCase(any()) } just Runs
        viewModel.updateUserName("Alice")
        advanceUntilIdle()
        coVerify { setUserNameUseCase("Alice") }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateUserName with empty string invokes SetUserNameUseCase`() = runTest {
        coEvery { setUserNameUseCase(any()) } just Runs
        viewModel.updateUserName("")
        advanceUntilIdle()
        coVerify { setUserNameUseCase("") }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateProfileImage invokes SetProfileImageUseCase with uri`() = runTest {
        coEvery { setProfileImageUseCase(any()) } just Runs
        val uri = "content://media/external/images/1234"
        viewModel.updateProfileImage(uri)
        advanceUntilIdle()
        coVerify { setProfileImageUseCase(uri) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateProfileImage with empty uri invokes SetProfileImageUseCase`() = runTest {
        coEvery { setProfileImageUseCase(any()) } just Runs
        viewModel.updateProfileImage("")
        advanceUntilIdle()
        coVerify { setProfileImageUseCase("") }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `multiple updates invoke correct use cases`() = runTest {
        coEvery { setDarkModeUseCase(any()) } just Runs
        coEvery { setUserNameUseCase(any()) } just Runs
        coEvery { setProfileImageUseCase(any()) } just Runs

        viewModel.toggleDarkMode(true)
        viewModel.updateUserName("Bob")
        viewModel.updateProfileImage("content://media/1")
        advanceUntilIdle()

        coVerify { setDarkModeUseCase(true) }
        coVerify { setUserNameUseCase("Bob") }
        coVerify { setProfileImageUseCase("content://media/1") }
    }

}