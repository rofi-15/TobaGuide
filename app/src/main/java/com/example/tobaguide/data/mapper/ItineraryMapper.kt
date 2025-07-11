package com.example.tobaguide.data.mapper

import com.example.tobaguide.data.local.entity.ItineraryEntity
import com.example.tobaguide.domain.model.ItineraryPlan

// Mengubah dari Entity (Database) ke Model (Domain/UI)
fun ItineraryEntity.toDomain(): ItineraryPlan {
    return ItineraryPlan(
        id = this.id,
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        participantCount = this.participantCount,
        dailyPlans = this.dayPlans, // <-- Perbaikan: dayPlans -> dailyPlans
        createdAt = this.createdAt,
        isManual = this.isManual,
        // Parameter tambahan yang tidak ada di Entity, gunakan default value
        packingList = emptyList(),
        notes = emptyList()
    )
}

// Mengubah dari Model (Domain/UI) ke Entity (Database)
fun ItineraryPlan.toEntity(): ItineraryEntity {
    return ItineraryEntity(
        id = this.id,
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        participantCount = this.participantCount,
        dayPlans = this.dailyPlans, // <-- Perbaikan: dailyPlans -> dayPlans
        createdAt = this.createdAt,
        isManual = this.isManual
        // packingList dan notes tidak disimpan di Entity (hanya di memory)
    )
}