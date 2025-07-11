package com.example.tobaguide.domain.model
data class DayPlan(
    val destination: String = "",
    val activities: List<ActivityEntry> = listOf(ActivityEntry())
)