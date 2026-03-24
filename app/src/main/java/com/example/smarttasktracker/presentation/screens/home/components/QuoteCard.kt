package com.example.smarttasktracker.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.domain.model.Quote
import com.example.smarttasktracker.presentation.mock.staticQuote
import com.example.smarttasktracker.presentation.theme.Primary
import com.example.smarttasktracker.presentation.theme.TextPrimary
import com.example.smarttasktracker.presentation.theme.TextSecondary
import compose.icons.FeatherIcons
import compose.icons.feathericons.BookOpen

@Composable
fun QuoteCard(quote: Quote) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
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
                text = "\" ${quote.text} \"",
                style = MaterialTheme.typography.bodyLarge,
                color = TextPrimary,
                textAlign = TextAlign.Start
            )
            Text(
                text = "- ${quote.author}",
                style = MaterialTheme.typography.labelMedium,
                color = TextSecondary,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview
@Composable
fun QuoteCardPreview() = QuoteCard(staticQuote)
