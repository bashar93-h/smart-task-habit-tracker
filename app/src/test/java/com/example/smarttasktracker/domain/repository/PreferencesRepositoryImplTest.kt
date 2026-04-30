package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.data.datasources.preferences.PreferencesRepositoryImpl
import com.example.smarttasktracker.data.datasources.preferences.UserPreferencesDataSource
import com.example.smarttasktracker.domain.model.UserPreferences
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
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PreferencesRepositoryImplTest {
    private lateinit var dataSource: UserPreferencesDataSource
    private lateinit var repository: PreferencesRepositoryImpl

    val fakePreferences = UserPreferences(
        userName = "Guest",
        profileImageUri = "",
        isDarkMode = false
    )

    @Before
    fun setup() {
        dataSource = mockk()
        repository = PreferencesRepositoryImpl(dataSource)
    }

    @Test
    fun `getUserPreferences returns flow from dataSource`() = runTest {
        every { dataSource.getUserPreferences() } returns flowOf(fakePreferences)

        val result = repository.getUserPreferences().first()

        Assert.assertEquals(fakePreferences, result)
        verify { dataSource.getUserPreferences() }
    }

    @Test
    fun `setDarkMode delegates to dataSource`() = runTest {
        coEvery { dataSource.setDarkMode(any()) } just Runs
        repository.setDarkMode(false)
        coVerify { dataSource.setDarkMode(false) }
    }

    @Test
    fun `setUserName delegates to dataSource`() = runTest {
        coEvery { dataSource.setUserName(any()) } just Runs
        repository.setUserName("name")
        coVerify { dataSource.setUserName("name") }
    }

    @Test
    fun `setProfileImageUri delegates to dataSource`() = runTest {
        val uri = "content://media/external/images/1234"
        coEvery { dataSource.setProfileImageUri(any()) } just Runs
        repository.setProfileImageUri(uri)
        coVerify { dataSource.setProfileImageUri(uri) }
    }

    @Test
    fun `getUserPreferences emits updated values after changes`() = runTest {
        val updatedPreferences = UserPreferences(
            userName = "Bashar",
            profileImageUri = "content://media/1",
            isDarkMode = true
        )
        every { dataSource.getUserPreferences() } returns flowOf(
            fakePreferences,
            updatedPreferences
        )

        val result = mutableListOf<UserPreferences>()
        repository.getUserPreferences().collect { result.add(it) }
        Assert.assertEquals(2, result.size)
        Assert.assertEquals(fakePreferences, result[0])
        Assert.assertEquals(updatedPreferences, result[1])
    }
}
