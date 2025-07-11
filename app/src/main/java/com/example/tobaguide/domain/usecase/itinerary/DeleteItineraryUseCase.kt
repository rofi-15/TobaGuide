package com.example.tobaguide.domain.usecase.itinerary

import com.example.tobaguide.domain.repository.IItineraryRepository
import javax.inject.Inject

class DeleteItineraryUseCase @Inject constructor(
    private val repository: IItineraryRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteItinerary(id)
}