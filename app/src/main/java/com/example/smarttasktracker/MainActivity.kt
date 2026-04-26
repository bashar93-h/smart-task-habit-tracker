package com.example.smarttasktracker

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smarttasktracker.core.notification.NotificationHelper
import com.example.smarttasktracker.presentation.navigation.AppNavigation
import com.example.smarttasktracker.presentation.screens.home.viewmodel.userPrefs.UserPreferencesViewModel
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: UserPreferencesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        NotificationHelper.createNotificationChannel(this)
        requestNotificationPermission()
        setContent {
            val preferences by viewModel.preferences.collectAsStateWithLifecycle()
            SmartTaskTrackerTheme(darkTheme = preferences.isDarkMode) {
                AppNavigation()
            }
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf( android.Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }
}


