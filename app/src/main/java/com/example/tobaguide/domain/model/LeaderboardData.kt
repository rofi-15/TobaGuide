package com.example.tobaguide.domain.model

import com.example.tobaguide.domain.model.User

data class LeaderboardData(
    val topThree: List<User>,
    val allUsers: List<User>,
    val currentUserRank: Int,
    val currentUserPoints: Int
)