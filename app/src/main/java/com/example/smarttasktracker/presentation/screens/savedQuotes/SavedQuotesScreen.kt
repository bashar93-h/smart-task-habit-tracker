package com.example.smarttasktracker.presentation.screens.savedQuotes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smarttasktracker.presentation.components.AppTopBar
import com.example.smarttasktracker.presentation.mock.mockSavedQuotes
import com.example.smarttasktracker.presentation.screens.savedQuotes.components.EmptyQuoteState
import com.example.smarttasktracker.presentation.screens.savedQuotes.components.SavedQuoteCard
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme

@Composable
fun SavedQuotesScreen(navController: NavController?) {
    val quotes = remember { mockSavedQuotes.toMutableStateList() }
    val context = LocalContext.current

    Scaffold(topBar = {
        AppTopBar("Saved Quotes", onBackClick = { navController?.popBackStack() })
<<<<<<< Updated upstream
    }, bottomBar = { AppBottomBar(navController) }) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) { }
=======
    }, bottomBar = { }) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = MaterialTheme.colorScheme.background,
        ) {
            if (quotes.isEmpty()) {
                EmptyQuoteState()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "${quotes.size} saved ${if (quotes.size == 1) "quote" else "quotes"}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    items(items = quotes, key = { it.id }) { quote ->
                        SavedQuoteCard(
                            quote = quote,
                            onUnsave = { quotes.removeAll { it.id == quote.id } },
                            onCopy = {
                                val clipboard =
                                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText(
                                    "quote",
                                    "\u201C${quote.text}\u201D — ${quote.author}"
                                )
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Quote copied!", Toast.LENGTH_LONG).show()
                            })
                    }
                }
            }

        }
>>>>>>> Stashed changes
    }
}


@Preview
@Composable
fun SavedQuotesScreenPreview() {
    SmartTaskTrackerTheme() {
        SavedQuotesScreen(null)
    }
}
