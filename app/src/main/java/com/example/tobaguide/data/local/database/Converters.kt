package com.example.tobaguide.data.local.database

import androidx.room.TypeConverter
import com.example.tobaguide.domain.model.DayPlan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromDailyPlanList(value: List<DayPlan>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toDailyPlanList(value: String?): List<DayPlan>? {
        val listType = object : TypeToken<List<DayPlan>>() {}.type
        return value?.let { gson.fromJson(it, listType) }
    }
}