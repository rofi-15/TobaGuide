package com.example.tobaguide.domain.repository

import com.example.tobaguide.domain.model.ItineraryPlan
import kotlinx.coroutines.flow.Flow

interface IItineraryRepository {
    fun getAllItineraries(): Flow<List<ItineraryPlan>>
    suspend fun getItineraryById(id: String): ItineraryPlan?
    suspend fun saveItinerary(itinerary: ItineraryPlan): Result<Unit>
    suspend fun updateItinerary(itinerary: ItineraryPlan): Result<Unit>
    suspend fun deleteItinerary(id: String): Result<Unit>
}

