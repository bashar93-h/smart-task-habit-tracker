package com.example.smarttasktracker.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smarttasktracker.presentation.mock.mockTasks
import com.example.smarttasktracker.presentation.theme.BadgeBlueBg
import com.example.smarttasktracker.presentation.theme.BadgeBlueTx
import com.example.smarttasktracker.presentation.theme.BadgeGreenBg
import com.example.smarttasktracker.presentation.theme.BadgeGreenTx
import com.example.smarttasktracker.presentation.theme.BadgePinkBg
import com.example.smarttasktracker.presentation.theme.BadgePinkTx
import com.example.smarttasktracker.presentation.theme.Primary
import com.example.smarttasktracker.presentation.theme.PrimaryLight
import com.example.smarttasktracker.presentation.theme.TextSecondary
import compose.icons.FeatherIcons
import compose.icons.feathericons.CheckSquare

@Composable
fun TodayActivitySection(modifier: Modifier = Modifier) {
    val tasks = mockTasks
    val completedCount = tasks.count { it.isCompleted }
    val totalCount = tasks.size
    val progress = completedCount.toFloat() / totalCount.toFloat()
    Card(
        modifier = modifier
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = FeatherIcons.CheckSquare,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Today's Tasks",
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                TextButton(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(
                        "View All",
                        style = MaterialTheme.typography.labelMedium,
                        color = Primary
                    )
                }

            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StatChip(
                    "Completed",
                    "$completedCount",
                    BadgeGreenTx,
                    BadgeGreenBg,
                    modifier = Modifier.weight(1f)
                )
                StatChip(
                    "Remaining",
                    "${totalCount - completedCount}",
                    BadgePinkTx,
                    BadgePinkBg,
                    modifier = Modifier.weight(1f)
                )
                StatChip(
                    "Total",
                    "$totalCount",
                    BadgeBlueTx,
                    BadgeBlueBg,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(PrimaryLight)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .height(7.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Primary)
                    )
                }
                Text(
                    text = "${(progress * 100).toInt()}% of today's tasks completed",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun StatChip(label: String, value: String, color: Color, bg: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = value,
            color = color,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = color.copy(alpha = 0.8f),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun TodayActivitySectionPreview() = TodayActivitySection()
