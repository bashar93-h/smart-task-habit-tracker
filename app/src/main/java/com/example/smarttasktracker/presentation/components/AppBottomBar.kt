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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.smarttasktracker.presentation.navigation.BottomNavItem
import com.example.smarttasktracker.presentation.navigation.Screen
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
        icon = FeatherIcons.Home,           // unselected
        selectedIcon = FeatherIcons.Home, // selected
        route = Screen.Home.route
    ),
    BottomNavItem(
        label = "Tasks",
        icon = FeatherIcons.Square,        // unselected
        selectedIcon = FeatherIcons.CheckSquare,   // selected
        route = Screen.Tasks.route
    ),
    BottomNavItem(
        label = "Habits",
        icon = FeatherIcons.Repeat,         // unselected
        selectedIcon = FeatherIcons.Circle,    // selected
        route = Screen.Habits.route
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomBar(navController: NavController?) {
    val navBackStackEntry = navController?.currentBackStackEntryAsState()?.value
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = TopBarBg,
        tonalElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .height(64.dp)
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route
            val iconSize by animateDpAsState(
                targetValue = if (isSelected) 26.dp else 22.dp,
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
                    MaterialTheme.colorScheme.onSurfaceVariant,
                animationSpec = tween(300),
                label = "labelColor"
            )

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController?.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
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
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
                ),
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}
