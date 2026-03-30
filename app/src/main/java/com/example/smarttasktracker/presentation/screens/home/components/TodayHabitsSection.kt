package com.example.smarttasktracker.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.presentation.mock.mockHabits
import com.example.smarttasktracker.presentation.navigation.Screen
import com.example.smarttasktracker.presentation.theme.Primary
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Repeat

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TodayHabitsSection(modifier: Modifier = Modifier, navController: NavController?) {

    val habits = mockHabits.toMutableList()
    val doneCount = habits.count { it.isDone }
    val totalCount = habits.size

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 20.dp),
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
                        imageVector = FeatherIcons.Repeat,
                        contentDescription = null,
                        tint = Primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Today's Habits",
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
                TextButton(
                    onClick = { navController?.navigate(Screen.Habits.route) },
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(
                        "View All",
                        style = MaterialTheme.typography.labelMedium,
                        color = Primary
                    )
                }
            }
            Text(
                text = "$doneCount of $totalCount habits completed today",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))

//            LazyVerticalGrid(
//                columns = GridCells.Adaptive(minSize = 120.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                itemsIndexed(habits) { index, habit ->
//                    HabitChip(
//                        habit = habit,
//                        onToggle = {
//                            habits[index] = habit.copy(isDone = !habit.isDone)
//                        }
//                    )
//                }
//            }
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                habits.forEachIndexed { index, habit ->
                    HabitChip(
                        habit = habit,
                        onToggle = {
                            habits[index] = habit.copy(isDone = !habit.isDone)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HabitChip(
    habit: HabitItem = mockHabits[0],
    onToggle: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (habit.isDone) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)

    val contentColor = if (habit.isDone) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .background(backgroundColor)
            .clickable { onToggle() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = habit.icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = habit.title, style = MaterialTheme.typography.labelMedium, color = contentColor)
        Spacer(modifier = Modifier.width(8.dp))
        if (habit.streak > 0) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = "🔥", fontSize = 10.sp)
                Text(
                    text = "${habit.streak}",
                    style = MaterialTheme.typography.labelSmall,
                    color = contentColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun TodayHabitsSectionPreview() {
    SmartTaskTrackerTheme() {
        TodayHabitsSection(navController = null)
    }
}
