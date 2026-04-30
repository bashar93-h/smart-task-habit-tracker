package com.example.smarttasktracker.domain.usecase.preferences

import com.example.smarttasktracker.domain.model.UserPreferences
import com.example.smarttasktracker.domain.repository.PreferencesRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserPreferencesUseCasesTest {
    private lateinit var repository: PreferencesRepository
    private lateinit var getUserPreferencesUseCase: GetUserPreferencesUseCase
    private lateinit var setDarkModeUseCase: SetDarkModeUseCase
    private lateinit var setUserNameUseCase: SetUserNameUseCase
    private lateinit var setProfileImageUseCase: SetProfileImageUseCase

    private val fakePreferences = UserPreferences(
        isDarkMode = false,
        userName = "Guest",
        profileImageUri = ""
    )

    @Before
    fun setup() {
        repository = mockk()
        getUserPreferencesUseCase = GetUserPreferencesUseCase(repository)
        setDarkModeUseCase = SetDarkModeUseCase(repository)
        setUserNameUseCase = SetUserNameUseCase(repository)
        setProfileImageUseCase = SetProfileImageUseCase(repository)
    }

    @Test
    fun `getUserPreferencesUseCase should return flow from repository`() = runTest {
        every { repository.getUserPreferences() } returns flowOf(fakePreferences)
        val result = getUserPreferencesUseCase().first()
        assertEquals(fakePreferences, result)
        verify { repository.getUserPreferences() }
    }

    @Test
    fun `getUserPreferencesUseCase emits multiple values`() = runTest {
        val updatedPreferences =
            fakePreferences.copy(isDarkMode = true, userName = "Bashar")
        every { repository.getUserPreferences() } returns flowOf(
            fakePreferences,
            updatedPreferences
        )
        val results = mutableListOf<UserPreferences>()
        getUserPreferencesUseCase().collect { results.add(it) }
        assertEquals(2, results.size)
        assertEquals(fakePreferences, results[0])
        assertEquals(updatedPreferences, results[1])
    }

    @Test
    fun `setDarkModeUseCase invokes repository with true`() = runTest {
        coEvery { repository.setDarkMode(any()) } just Runs
        setDarkModeUseCase(true)
        coVerify { repository.setDarkMode(true) }
    }

    @Test
    fun `setDarkModeUseCase invokes repository with false`() = runTest {
        coEvery { repository.setDarkMode(any()) } just Runs
        setDarkModeUseCase(false)
        coVerify { repository.setDarkMode(false) }
    }

    @Test
    fun `setUserNameUseCase invokes repository with empty name`() = runTest {
        coEvery { repository.setUserName(any()) } just Runs
        setUserNameUseCase("Bashar")
        coVerify { repository.setUserName("Bashar") }
    }

    @Test
    fun `setUserNameUseCase invokes repository with given name`() = runTest {
        coEvery { repository.setUserName(any()) } just Runs
        setUserNameUseCase("")
        coVerify { repository.setUserName("") }
    }

    @Test
    fun `setProfileImageUseCase invokes repository with valid uri`() = runTest {
        val uri = "content://media/external/images/1234"
        coEvery { repository.setProfileImageUri(any()) } just Runs
        setProfileImageUseCase(uri)
        coVerify { repository.setProfileImageUri(uri) }
    }


}