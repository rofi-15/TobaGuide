package com.example.tobaguide.domain.usecase.itinerary

import com.example.tobaguide.domain.model.ItineraryPlan
import com.example.tobaguide.domain.repository.IItineraryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserItinerariesUseCase @Inject constructor(
    private val itineraryRepository: IItineraryRepository
) {
    operator fun invoke(): Flow<List<ItineraryPlan>> {
        return itineraryRepository.getAllItineraries()
    }
}

