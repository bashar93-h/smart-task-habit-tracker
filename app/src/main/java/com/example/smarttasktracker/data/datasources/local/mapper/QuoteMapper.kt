package com.example.smarttasktracker.data.datasources.local.mapper

import com.example.smarttasktracker.data.datasources.local.entity.QuoteEntity
import com.example.smarttasktracker.domain.model.SavedQuote
import java.time.LocalDate

fun QuoteEntity.toDomain(): SavedQuote {
    return SavedQuote(
        id = id,
        text = text,
        author = author,
        dateSaved = LocalDate.parse(dateSaved)
    )
}

fun SavedQuote.toEntity(): QuoteEntity {
    return QuoteEntity(
        id = id,
        text = text,
        author = author,
        dateSaved = dateSaved.toString()
    )
}