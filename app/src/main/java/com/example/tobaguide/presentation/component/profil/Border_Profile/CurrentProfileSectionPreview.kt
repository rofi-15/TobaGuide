package com.example.profileborder

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CurrentProfileData(
    val initials: String,
    val name: String,
    val level: String,
    val currentPoints: Int,
    val pointsToNext: Int,
    val nextLevel: String,
    val borderColor: Color
)

@Composable
fun CurrentProfileSection(
    profileData: CurrentProfileData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Profile Section
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Avatar with Border
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.1f))
                        .border(
                            width = 4.dp,
                            color = profileData.borderColor,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profileData.initials,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Profile Info
                Column {
                    Text(
                        text = profileData.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${profileData.level} • ${profileData.currentPoints} points",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Progress to Next Level
            Text(
                text = "${profileData.pointsToNext} points to ${profileData.nextLevel}",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentProfileSectionPreview() {
    CurrentProfileSection(
        profileData = CurrentProfileData(
            initials = "JT",
            name = "John Traveler",
            level = "Gold Level",
            currentPoints = 2150,
            pointsToNext = 850,
            nextLevel = "Platinum Level",
            borderColor = Color(0xFFFFD700)
        )
    )
}