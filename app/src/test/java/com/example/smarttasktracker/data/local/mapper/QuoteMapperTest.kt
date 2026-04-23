package com.example.smarttasktracker.data.local.mapper

import com.example.smarttasktracker.data.datasources.local.entity.QuoteEntity
import com.example.smarttasktracker.data.datasources.local.mapper.toDomain
import com.example.smarttasktracker.data.datasources.local.mapper.toEntity
import com.example.smarttasktracker.domain.model.SavedQuote
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class QuoteMapperTest {
    @Test
    fun `toDomain should quoteEntity to savedQuote correctly`() = runTest {
        val entity = QuoteEntity(
            id = "1",
            author = "Author",
            text = "Quote",
            dateSaved = LocalDate.now().toString()
        )
        val domain = entity.toDomain()
        assertEquals(entity.id, domain.id)
        assertEquals(entity.text, domain.text)
        assertEquals(entity.author, domain.author)
        assertEquals(LocalDate.parse(entity.dateSaved), domain.dateSaved)
    }

    @Test
    fun `toEntity should savedQuote to quoteEntity correctly`() = runTest {
        val domain = SavedQuote(
            id = "1",
            author = "Author",
            text = "Quote",
            dateSaved = LocalDate.now()
        )
        val entity = domain.toEntity()
        assertEquals(domain.id, entity.id)
        assertEquals(domain.text, entity.text)
        assertEquals(domain.author, entity.author)
        assertEquals(LocalDate.now().toString(), entity.dateSaved)
    }
}