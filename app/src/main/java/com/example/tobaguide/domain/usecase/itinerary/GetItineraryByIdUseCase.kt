package com.example.tobaguide.domain.usecase.itinerary

import com.example.tobaguide.domain.model.ItineraryPlan
import com.example.tobaguide.domain.repository.IItineraryRepository
import javax.inject.Inject

/**
 * Use case yang bertanggung jawab HANYA untuk mengambil satu itinerary
 * berdasarkan ID-nya.
 */
class GetItineraryByIdUseCase @Inject constructor(
    private val repository: IItineraryRepository
) {
    // Karena getItineraryById di repository adalah suspend function,
    // UseCase ini juga harus suspend.
    suspend operator fun invoke(id: String): ItineraryPlan? {
        // Memanggil fungsi dari 'kontrak' repository
        return repository.getItineraryById(id)
    }
}
