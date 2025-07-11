package com.example.tobaguide.presentation.component.profil.Tampilan_Awal_Profil

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RewardHistoryItem(
    val title: String,
    val description: String,
    val points: String,
    val timeAgo: String
)

@Composable
fun CardInformasi(
    onProfileClick: () -> Unit = {},
    onRewardsHistoryClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("Profile") }

    Column(
        modifier = Modifier
            .padding(start = 23.dp, end = 23.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (selectedTab == "Profile") {
                Button(
                    onClick = {
                        selectedTab = "Profile"
                        onProfileClick()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0F4C3A)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Profile", color = Color.White)
                }
            } else {
                OutlinedButton(
                    onClick = {
                        selectedTab = "Profile"
                        onProfileClick()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0F4C3A)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Profile")
                }
            }

            if (selectedTab == "Rewards History") {
                Button(
                    onClick = {
                        selectedTab = "Rewards History"
                        onRewardsHistoryClick()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0F4C3A)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Rewards History", color = Color.White)
                }
            } else {
                OutlinedButton(
                    onClick = {
                        selectedTab = "Rewards History"
                        onRewardsHistoryClick()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0F4C3A)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Rewards History")
                }
            }
        }

        when (selectedTab) {
            "Profile" -> {
                ProfileContent(onEditProfileClick = onEditProfileClick)
            }
            "Rewards History" -> {
                RewardsHistoryContent()
            }
        }
    }
}

@Composable
fun ProfileContent(
    onEditProfileClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp, end = 0.dp, top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Informasi Account",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onEditProfileClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Info",
                    tint = Color.Black
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp,
                pressedElevation = 6.dp,
                focusedElevation = 3.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                AccountInfoItem(
                    label = "Nama",
                    value = "Roji Fahrofi",
                    color = Color.Black
                )
                AccountInfoItem(
                    label = "Username",
                    value = "Roji",
                    color = Color.Black
                )
                AccountInfoItem(
                    label = "Email",
                    value = "rofianakbaik@ayam.com",
                    color = Color.Black
                )
                AccountInfoItem(
                    label = "Nomor Telepon",
                    value = "+62882-2238-1678",
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun RewardsHistoryContent() {
    val rewardHistoryItems = remember {
        listOf(
            RewardHistoryItem(
                title = "Memberi Ulasan Wisata",
                description = "Menaikkan 400 exp!",
                points = "400",
                timeAgo = "1H"
            ),
            RewardHistoryItem(
                title = "Memberi Ulasan Wisata",
                description = "Menaikkan 400 exp!",
                points = "400",
                timeAgo = "7H"
            ),
            RewardHistoryItem(
                title = "Memberi Ulasan Wisata",
                description = "Menaikkan 400 exp!",
                points = "400",
                timeAgo = "14H"
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(rewardHistoryItems) { item ->
            RewardHistoryCard(item)
        }
    }
}

@Composable
fun RewardHistoryCard(item: RewardHistoryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PointCircle(points = item.points, achieved = true)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color(0xFFFFB300)
                )
            }

            Text(
                text = item.timeAgo,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun PointCircle(points: String, achieved: Boolean) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(if (achieved) Color(0xFFFFB000) else Color.White)
            .border(
                2.dp,
                if (achieved) Color(0xFFFFB000) else Color(0xFFCCCCCC),
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = points,
            color = if (achieved) Color.White else Color(0xFF999999),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AccountInfoItem(label: String, value: String, color: Color = Color.Black) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )

        Text(
            text = value,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = color
        )
    }
}

@Preview
@Composable
fun CardInformasiPreview() {
    CardInformasi(
        onProfileClick = {
            println("Profile button clicked")
        },
        onRewardsHistoryClick = {
            println("Rewards History button clicked")
        },
        onEditProfileClick = {
            println("Edit Profile button clicked")
        }
    )
}