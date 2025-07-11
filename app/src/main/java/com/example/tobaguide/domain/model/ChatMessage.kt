package com.example.tobaguide.domain.model
data class ChatMessage(
    val id: String,
    val message: String,
    val timestamp: String,
    val isFromUser: Boolean,
    val isRecommendation: Boolean = false,
    val recommendations: List<String> = emptyList()
)