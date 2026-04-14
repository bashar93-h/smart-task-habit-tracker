package com.example.smarttasktracker.data.datasources.local.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromBooleanList(value: List<Boolean>): String {
        return value.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun toBooleanList(value: String): List<Boolean> {
        if (value.isEmpty()) return emptyList()
        return value.split(",").map { it.toBoolean() }
    }
}
