package com.example.smarttasktracker.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smarttasktracker.presentation.screens.home.viewmodel.QuoteViewModel
import com.example.smarttasktracker.presentation.theme.Primary
import com.example.smarttasktracker.presentation.theme.TextPrimary
import com.example.smarttasktracker.presentation.theme.TextSecondary
import com.example.smarttasktracker.presentation.utils.turncate
import compose.icons.FeatherIcons
import compose.icons.feathericons.BookOpen

@Composable
fun QuoteCard(viewModel: QuoteViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog && state.quote != null) {
        QuoteDialog(
            quote = state.quote!!,
            onDismiss = { showDialog = false },
            onSaveToFavorites = { TODO() })
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDialog = true },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        if (state.isLoadingQuote) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (state.quoteError.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.quoteError, color = MaterialTheme.colorScheme.error)
                Text(
                    text = "Retry",
                    color = Primary,
                    modifier = Modifier
                        .clickable { viewModel.getRandomQuote() }
                        .padding(top = 8.dp)
                )
            }
        }
        state.quote?.let {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = FeatherIcons.BookOpen,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Daily Motivation",
                        style = MaterialTheme.typography.labelMedium,
                        color = Primary
                    )
                }

                Text(
                    text = "\" ${it.text.turncate(70)} \"",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "- ${it.author}",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Preview
@Composable
fun QuoteCardPreview() = QuoteCard()
