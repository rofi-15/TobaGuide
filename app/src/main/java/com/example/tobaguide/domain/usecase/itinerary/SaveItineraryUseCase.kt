package com.example.tobaguide.domain.usecase.itinerary

import com.example.tobaguide.domain.model.ItineraryPlan
import com.example.tobaguide.domain.repository.IItineraryRepository
import javax.inject.Inject

class SaveItineraryUseCase @Inject constructor(
    private val itineraryRepository: IItineraryRepository
) {
    suspend operator fun invoke(itinerary: ItineraryPlan): Result<Unit> {
        return if (validateItinerary(itinerary)) {
            itineraryRepository.saveItinerary(itinerary)
        } else {
            Result.failure(IllegalArgumentException("Invalid itinerary data"))
        }
    }

    private fun validateItinerary(itinerary: ItineraryPlan): Boolean {
        return itinerary.title.isNotBlank() &&
                itinerary.startDate.isNotBlank() &&
                itinerary.endDate.isNotBlank() &&
                itinerary.participantCount > 0 &&
                itinerary.dailyPlans.isNotEmpty()
    }
}