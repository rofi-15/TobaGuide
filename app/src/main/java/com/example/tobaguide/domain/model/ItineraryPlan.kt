package com.example.tobaguide.domain.model

data class ItineraryPlan(
    val id: String = "",
    val title: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val participantCount: Int = 0,
    val dailyPlans: List<DayPlan> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val isManual: Boolean = true,
    val packingList: List<ChecklistItem> = emptyList(),
    val notes: List<ChecklistItem> = emptyList()
)