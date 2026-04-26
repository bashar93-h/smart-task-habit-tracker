package com.example.smarttasktracker.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.smarttasktracker.presentation.navigation.BottomNavItem
import com.example.smarttasktracker.presentation.navigation.Screen
import com.example.smarttasktracker.presentation.theme.Primary
import com.example.smarttasktracker.presentation.theme.SmartTaskTrackerTheme
import com.example.smarttasktracker.presentation.theme.TextPrimary
import com.example.smarttasktracker.presentation.theme.TextSecondary
import com.example.smarttasktracker.presentation.theme.TopBarBg
import compose.icons.FeatherIcons
import compose.icons.feathericons.CheckSquare
import compose.icons.feathericons.Circle
import compose.icons.feathericons.Home
import compose.icons.feathericons.Repeat
import compose.icons.feathericons.Square

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Home",
        icon = FeatherIcons.Home,
        selectedIcon = FeatherIcons.Home,
        route = Screen.Home.route
    ),
    BottomNavItem(
        label = "Tasks",
        icon = FeatherIcons.Square,
        selectedIcon = FeatherIcons.CheckSquare,
        route = Screen.Tasks.route
    ),
    BottomNavItem(
        label = "Habits",
        icon = FeatherIcons.Repeat,
        selectedIcon = FeatherIcons.Circle,
        route = Screen.Habits.route
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(navController: NavController?) {
    val navBackStackEntry = navController?.currentBackStackEntryAsState()?.value
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route
            val iconSize by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 22.dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                ),
                label = "iconSize"
            )

            val labelColor by animateColorAsState(
                targetValue = if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                animationSpec = tween(300),
                label = "labelColor"
            )

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController?.navigate(item.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall.copy(
                            lineHeight = 12.sp
                        ),
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = labelColor,
                        maxLines = 1,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    indicatorColor = Color.Transparent,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    SmartTaskTrackerTheme() {
        AppBottomBar(null)
    }
}
