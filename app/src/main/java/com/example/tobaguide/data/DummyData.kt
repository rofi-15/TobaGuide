package com.example.tobaguide.data

import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.tobaguide.domain.model.LeaderboardData
import com.example.tobaguide.domain.model.User
import com.example.tobaguide.presentation.component.home.List_Wisata.DestinationItem

object DummyData {
    fun getLeaderboardData(): LeaderboardData {
        val users = listOf(
            User("1", "Armika Rahman Lubis", 8216, "AR", Color(0xFF4FC3F7)),
            User("2", "Diva Azolla Angely", 8201, "DA", Color(0xFF4FC3F7)),
            User("3", "Sandio Vegas Consonadu", 7887, "SV", Color(0xFF4FC3F7)),
            User("4", "Nevano Panggabean", 6724, "NP", Color(0xFFE1BEE7)),
            User("5", "Wahiq A", 23009, "WA", Color(0xFF4FC3F7)),
            User("6", "Aulia S", 25880, "AS", Color(0xFFFFB74D)),
            User("7", "Rifky A", 23000, "RA", Color(0xFF4FC3F7))
        )

        val sortedUsers = users.sortedByDescending { it.points }
        val topThree = sortedUsers.take(3)

        return LeaderboardData(
            topThree = topThree,
            allUsers = sortedUsers,
            currentUserRank = 7,
            currentUserPoints = 7887
        )

    }
    val destinations = listOf(
            DestinationItem("1", "Desa Paropo", "4.1", "desa_paropo"),
            DestinationItem("2", "Air Terjun Efrata", "4.6", "air_terjun_efrata"),
            DestinationItem("3", "Batu Gantung", "4.2", "batu_gantung"),
            DestinationItem("4", "Bukit Cinta", "4.3", "bukit_cinta"),
            DestinationItem("5", "Pantai Bebas", "4.5", "pantai_bebas"),
            DestinationItem("6", "Danau Toba", "4.8", "danau_toba"),
            DestinationItem("7", "Pulau Samosir", "4.7", "pulau_samosir"),
            DestinationItem("9", "Hot Spring", "4.4", "hot_spring"),
            DestinationItem("10", "Hot Spring", "4.4", "hot_spring")
        )
}