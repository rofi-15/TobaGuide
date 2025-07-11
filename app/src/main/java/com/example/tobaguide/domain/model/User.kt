package com.example.tobaguide.domain.model

import androidx.compose.ui.graphics.Color

data class User(
    val id: String,
    val name: String,
    val points: Int,
    val avatarInitials: String,
    val avatarColor: Color
)