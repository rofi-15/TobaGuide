package com.example.tobaguide.presentation.screens.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.domain.model.DataClass
import com.example.tobaguide.presentation.component.reward.LeaderboardAtas
import com.example.tobaguide.presentation.component.reward.ListNama
import com.example.tobaguide.presentation.component.reward.Podium
import com.example.tobaguide.presentation.component.reward.TabPilihan
import com.example.tobaguide.presentation.component.reward.Peringkat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen() {
    var selectedTab by remember { mutableStateOf("MONTHLY") }

    val sampleUsers = listOf(
        DataClass("Annisa R", "8,216", 1, 58, "ARL", Color(0xFFFF9800)),
        DataClass("Diva Azalia", "8,201", 2, 58, "DA", Color(0xFF2196F3)),
        DataClass("Sancio V.", "7,897", 3, 50, "SVC", Color(0xFF00BCD4)),
        DataClass("John Doe", "7,543", 4, 45, "JD", Color(0xFF4CAF50)),
        DataClass("Jane Smith", "7,321", 5, 42, "JS", Color(0xFF9C27B0)),
        DataClass("Alex Johnson", "6,890", 6, 38, "AJ", Color(0xFFFF5722)),
        DataClass("Sarah Wilson", "6,543", 7, 35, "SW", Color(0xFF795548)),
        DataClass("Mike Brown", "6,234", 8, 32, "MB", Color(0xFF607D8B)),
        DataClass("Lisa Davis", "5,987", 9, 30, "LD", Color(0xFFE91E63)),
        DataClass("Tom Anderson", "5,765", 10, 28, "TA", Color(0xFF3F51B5))
    )

    val currentUserRank = 7
    val currentUserPoints = "4,930"
    val currentUserPosition = 13

    Box(
        modifier = Modifier.background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(top = 0.dp, bottom = 16.dp)
        ) {
            item {
                LeaderboardAtas()
            }
            item {
                if (sampleUsers.size >= 3) {
                    Podium(
                        first = sampleUsers[0],
                        second = sampleUsers[1],
                        third = sampleUsers[2]
                    )
                }
            }

            item {
                TabPilihan(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            }

            item {
                Peringkat(
                    rank = currentUserRank,
                    points = currentUserPoints,
                    position = currentUserPosition
                )
            }

            item {
                Spacer(modifier = Modifier.height(4.dp))
            }

            itemsIndexed(
                items = if (sampleUsers.size > 3) sampleUsers.drop(3) else emptyList()
            ) { index, user ->
                ListNama(
                    user = user,
                    rank = index + 4
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LeaderboardScreenPreview() {
    MaterialTheme {
        LeaderboardScreen()
    }
}