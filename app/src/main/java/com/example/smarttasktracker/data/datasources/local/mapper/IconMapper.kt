package com.example.smarttasktracker.data.datasources.local.mapper

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.FeatherIcons
import compose.icons.feathericons.*

object IconMapper {
    private val iconMap = mapOf(
        "Droplet" to FeatherIcons.Droplet,
        "Moon" to FeatherIcons.Moon,
        "Activity" to FeatherIcons.Activity,
        "BookOpen" to FeatherIcons.BookOpen,
        "Sunset" to FeatherIcons.Sunset,
        "Edit" to FeatherIcons.Edit,
        "Coffee" to FeatherIcons.Coffee,
        "Music" to FeatherIcons.Music,
        "Heart" to FeatherIcons.Heart,
        "Star" to FeatherIcons.Star,
        "Smile" to FeatherIcons.Smile,
        "Sun" to FeatherIcons.Sun,
        "Zap" to FeatherIcons.Zap,
        "Wind" to FeatherIcons.Wind,
        "Watch" to FeatherIcons.Watch,
        "Target" to FeatherIcons.Target,
        "TrendingUp" to FeatherIcons.TrendingUp,
        "Wifi" to FeatherIcons.Wifi,
        "Headphones" to FeatherIcons.Headphones,
        "Camera" to FeatherIcons.Camera,
    )

    fun toImageVector(name: String) : ImageVector  =
        iconMap[name] ?: FeatherIcons.Star

    fun toIconName(icon: ImageVector) : String =
        iconMap.entries.find { it.value== icon }?.key ?: "Star"

}

