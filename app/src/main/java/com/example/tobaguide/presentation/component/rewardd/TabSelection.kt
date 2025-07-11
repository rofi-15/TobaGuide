package com.example.tobaguide.presentation.component.reward

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.tobaguide.data.DummyData
import com.example.tobaguide.domain.model.User

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab() {
    val leaderboardData = remember { DummyData.getLeaderboardData() }
    var selectedTab by remember { mutableStateOf("MONTHLY") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        TabSelection(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        CurrentUserPosition(
            rank = leaderboardData.currentUserRank,
            points = leaderboardData.currentUserPoints
        )

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            itemsIndexed(leaderboardData.allUsers.take(4)) { index, user ->
                LeaderboardItem(
                    rank = index + 1,
                    user = user
                )
            }
        }
    }
}

@Composable
fun TopThreePodium(topThree: List<User>) {
    if (topThree.size < 3) return

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        PodiumUser(
            user = topThree[1],
            position = 2,
            height = 80.dp
        )
        PodiumUser(
            user = topThree[0],
            position = 1,
            height = 100.dp
        )
        PodiumUser(
            user = topThree[2],
            position = 3,
            height = 60.dp
        )
    }
}

@Composable
fun PodiumUser(
    user: User,
    position: Int,
    height: Dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        if (position == 1) {
            Text(
                text = "👑",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(user.avatarColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = user.avatarInitials,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(
                    when (position) {
                        1 -> Color(0xFFFFD700)
                        2 -> Color(0xFFC0C0C0)
                        else -> Color(0xFFCD7F32)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = position.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.name.split(" ").take(2).joinToString(" "),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
        Text(
            text = user.points.toString(),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(height)
                .background(
                    when (position) {
                        1 -> Color(0xFFFFD700)
                        2 -> Color(0xFFC0C0C0)
                        else -> Color(0xFFCD7F32)
                    },
                    RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                )
        )
    }
}

@Composable
fun TabSelection(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2E7D32), RoundedCornerShape(25.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf("WEEKLY", "MONTHLY", "ALL TIME").forEach { tab ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        if (selectedTab == tab) Color(0xFF205823)
                        else Color.Transparent
                    )
                    .clickable { onTabSelected(tab) }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = if (selectedTab == tab) Color.White else Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun CurrentUserPosition(
    rank: Int,
    points: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0B2)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "YOUR POSITION",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6F00)
                )
                Text(
                    text = "Rank: #$rank",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Text(
                text = "Points: $points",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6F00)
            )
        }
    }
}

@Composable
fun LeaderboardItem(
    rank: Int,
    user: User
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$rank.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(user.avatarColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user.avatarInitials,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = "Completed 10 levels",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = user.points.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}