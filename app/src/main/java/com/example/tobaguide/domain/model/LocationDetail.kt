package com.example.tobaguide.domain.model

data class LocationDetail(
    val id: String = "",
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val photos: List<LocationPhoto> = emptyList(),
    val hasWarning: Boolean = false,
    val description: String = "",
    val address: String = "",
    val category: String = "",
    val rating: Float = 0f,
    val reviewCount: Int = 0
)
data class LocationPhoto(
    val id: String,
    val url: String,
    val caption: String = "",
    val thumbnailUrl: String = ""
)
