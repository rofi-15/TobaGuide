package com.example.tobaguide.domain.model

import androidx.compose.ui.graphics.Color

data class DataClass(
    val name: String,
    val points: String,
    val rank: Int,
    val completedTasks: Int,
    val avatar: String,
    val color: Color
)