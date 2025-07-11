package com.example.tobaguide.domain.model
import java.util.UUID
data class ActivityEntry(
    val id: String = UUID.randomUUID().toString(),
    val description: String = "Aktivitas Baru",
    val time: String = "09:00 - 10:00",
    val cost: Int = 0
)