package com.example.tobaguide.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.tobaguide.domain.model.DayPlan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "itinerary_table")
data class ItineraryEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val participantCount: Int,
    val dayPlans: List<DayPlan>,
    val createdAt: Long,
    val isManual: Boolean
)
