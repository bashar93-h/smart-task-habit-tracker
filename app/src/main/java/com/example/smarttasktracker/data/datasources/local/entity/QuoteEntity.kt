package com.example.smarttasktracker.data.datasources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey
    val id : String,
    val text: String,
    val author: String,
    val dateSaved: String
)