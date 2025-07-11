package com.example.tobaguide.presentation.state

import com.example.tobaguide.domain.model.ItineraryPlan

data class ItineraryUiState(
    val isLoading: Boolean = false,
    val itineraries: List<ItineraryPlan> = emptyList(),
    val errorMessage: String? = null,
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)

