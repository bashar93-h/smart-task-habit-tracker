package com.example.smarttasktracker.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.smarttasktracker.presentation.theme.TextPrimary
import com.example.smarttasktracker.presentation.theme.TextSecondary
import com.example.smarttasktracker.presentation.theme.TopBarBg
import compose.icons.FeatherIcons
import compose.icons.feathericons.Menu
import compose.icons.feathericons.Zap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String, onMenuClick: () -> Unit, onQuoteClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }, navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(imageVector = FeatherIcons.Menu, contentDescription = "Toggle Menu")
            }
        }, actions = {
            IconButton(onClick = onQuoteClick) {
                Icon(
                    imageVector = FeatherIcons.Zap,
                    contentDescription = "Motivation Quote"
                )
            }
        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TopBarBg,
            titleContentColor = TextPrimary,
            navigationIconContentColor = TextSecondary,
            actionIconContentColor = TextSecondary
        )
    )
}
