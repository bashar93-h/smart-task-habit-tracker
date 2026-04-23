package com.example.smarttasktracker.data.remote.mapper

import com.example.smarttasktracker.data.datasources.remote.dto.QuoteDto
import com.example.smarttasktracker.data.datasources.remote.mapper.toDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class QuoteMapperTest {

    @Test
    fun `toDomain should map quoteDto to domain Quote model correctly`() {
        val quoteDto = QuoteDto(
            id = 1,
            author = "Author",
            quote = "Quote"
        )

        val domain = quoteDto.toDomain()

        assertEquals("1", domain.id)
        assertEquals(quoteDto.author, domain.author)
        assertEquals(quoteDto.quote, domain.text)
    }
}