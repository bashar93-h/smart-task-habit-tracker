package com.example.smarttasktracker.data.datasources.remote

import com.example.smarttasktracker.data.datasources.remote.dto.QuoteDto
import retrofit2.http.GET

interface QuoteApiServices {
    @GET("random")
    suspend fun getRandomQuote(): QuoteDto
}
