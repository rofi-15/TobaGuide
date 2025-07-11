package com.example.tobaguide.presentation.screens.profil

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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

data class BorderItemData(
    val name: String,
    val level: String,
    val points: Int,
    val isUnlocked: Boolean,
    val isCurrent: Boolean,
    val borderColor: Color,
    val pointsAway: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBorderScreen(
    onBackClick: () -> Unit = {}
) {
    var currentProfileData by remember {
        mutableStateOf(
            CurrentProfileData(
                initials = "RF",
                name = "Roji Fahrofi",
                level = "Gold Level",
                currentPoints = 2150,
                pointsToNext = 850,
                nextLevel = "Platinum Level",
                borderColor = Color(0xFFFFB000) // Menggunakan warna yang sama dengan profil
            )
        )
    }

    val initialBorderItems = listOf(
        BorderItemData(
            name = "Bronze Border",
            level = "Starter border",
            points = 0,
            isUnlocked = true,
            isCurrent = false,
            borderColor = Color(0xFFCD7F32)
        ),
        BorderItemData(
            name = "Silver Border",
            level = "Silver level",
            points = 500,
            isUnlocked = true,
            isCurrent = false,
            borderColor = Color(0xFFC0C0C0)
        ),
        BorderItemData(
            name = "Gold Border",
            level = "Gold level",
            points = 1500,
            isUnlocked = true,
            isCurrent = true,
            borderColor = Color(0xFFFFB000) // Menggunakan warna yang sama dengan profil
        ),
        BorderItemData(
            name = "Platinum Border",
            level = "Platinum level",
            points = 3000,
            isUnlocked = false,
            isCurrent = false,
            borderColor = Color(0xFFE5E4E2),
            pointsAway = 850
        ),
        BorderItemData(
            name = "Diamond Border",
            level = "Diamond level",
            points = 5000,
            isUnlocked = false,
            isCurrent = false,
            borderColor = Color(0xFFB9F2FF),
            pointsAway = 2850
        )
    )

    var borderItemsState by remember { mutableStateOf(initialBorderItems) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Profile Border",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                CurrentProfileSection(
                    profileData = currentProfileData
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                BordersCollectionSection()
            }

            items(borderItemsState) { borderItem ->
                BorderItem(
                    borderData = borderItem,
                    onApplyClick = {
                        if (borderItem.isUnlocked && !borderItem.isCurrent) {
                            // Update current profile dengan border yang dipilih
                            currentProfileData = currentProfileData.copy(
                                borderColor = borderItem.borderColor,
                                level = "${borderItem.name.split(" ")[0]} Level"
                            )

                            // Update status border items
                            borderItemsState = borderItemsState.map { item ->
                                item.copy(isCurrent = item.name == borderItem.name)
                            }
                        }
                    }
                )
            }

            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun CurrentProfileSection(profileData: CurrentProfileData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Avatar - Menggunakan desain yang sama dengan profil asli
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F4FD))
                    .border(4.dp, profileData.borderColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(90.dp)) {
                    val center = Offset(size.width / 2, size.height / 2)
                    val personColor = Color(0xFF4A90E2)

                    // Draw head
                    val headRadius = size.width * 0.18f
                    val headCenter = Offset(center.x, center.y - size.height * 0.22f)
                    drawCircle(
                        color = personColor,
                        radius = headRadius,
                        center = headCenter
                    )

                    // Draw neck
                    val neckWidth = size.width * 0.08f
                    val neckHeight = size.height * 0.12f
                    drawRect(
                        color = personColor,
                        topLeft = Offset(
                            center.x - neckWidth / 2,
                            headCenter.y + headRadius - size.height * 0.02f
                        ),
                        size = androidx.compose.ui.geometry.Size(neckWidth, neckHeight)
                    )

                    val shoulderWidth = size.width * 0.65f
                    val shoulderHeight = size.height * 0.35f
                    val shoulderTop = center.y + size.height * 0.08f

                    drawRoundRect(
                        color = personColor,
                        topLeft = Offset(center.x - shoulderWidth / 2, shoulderTop),
                        size = androidx.compose.ui.geometry.Size(shoulderWidth, shoulderHeight),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                            size.width * 0.15f,
                            size.width * 0.15f
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = profileData.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )

            Text(
                text = profileData.level,
                color = Color(0xFF666666),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Progress Section
            Text(
                text = "${profileData.currentPoints} / ${profileData.currentPoints + profileData.pointsToNext} points",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { profileData.currentPoints.toFloat() / (profileData.currentPoints + profileData.pointsToNext) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = profileData.borderColor,
                trackColor = Color(0xFFE0E0E0)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${profileData.pointsToNext} points to ${profileData.nextLevel}",
                fontSize = 12.sp,
                color = Color(0xFF999999)
            )
        }
    }
}

@Composable
fun BordersCollectionSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Text(
            text = "Borders Collection",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun BorderItem(
    borderData: BorderItemData,
    onApplyClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Border Preview
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F4FD))
                    .border(3.dp, borderData.borderColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "RF",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF4A90E2)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Border Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = borderData.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = borderData.level,
                    fontSize = 12.sp,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(top = 2.dp)
                )
                Text(
                    text = "${borderData.points} points required",
                    fontSize = 11.sp,
                    color = Color(0xFF999999),
                    modifier = Modifier.padding(top = 2.dp)
                )

                if (!borderData.isUnlocked && borderData.pointsAway != null) {
                    Text(
                        text = "${borderData.pointsAway} points away",
                        fontSize = 11.sp,
                        color = Color(0xFFFF6B6B),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            // Action Button
            when {
                borderData.isCurrent -> {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Current",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(24.dp)
                    )
                }
                borderData.isUnlocked -> {
                    Button(
                        onClick = onApplyClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = borderData.borderColor
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = "Apply",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
                else -> {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked",
                        tint = Color(0xFF999999),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileBorderScreenPreview() {
    ProfileBorderScreen(
        onBackClick = {
            println("Back clicked")
        }
    )
}