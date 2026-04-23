package com.example.smarttasktracker.data.datasources.remote.mapper

import com.example.smarttasktracker.data.datasources.remote.dto.QuoteDto
import com.example.smarttasktracker.domain.model.Quote

fun QuoteDto.toDomain(): Quote {
    return Quote(
        id = id.toString(),
        author = author,
        text = quote
    )
}
