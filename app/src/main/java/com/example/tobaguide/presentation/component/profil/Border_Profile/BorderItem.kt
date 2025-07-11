package com.example.profileborder

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
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

data class BorderItemData(
    val name: String,
    val level: String,
    val points: Int,
    val isUnlocked: Boolean,
    val isCurrent: Boolean,
    val borderColor: Color,
    val pointsAway: Int? = null
)

@Composable
fun BorderItem(
    borderData: BorderItemData,
    onApplyClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
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
            // Profile Border Circle
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.1f))
                    .border(
                        width = 3.dp,
                        color = if (borderData.isUnlocked) borderData.borderColor else Color.Gray,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (borderData.isUnlocked) {
                    Text(
                        text = "JT",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Border Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = borderData.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "${borderData.level} • ${borderData.points} points",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Status Badge
                when {
                    borderData.isCurrent -> {
                        Text(
                            text = "CURRENT",
                            fontSize = 12.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(
                                    Color.Blue.copy(alpha = 0.1f),
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    borderData.isUnlocked -> {
                        Text(
                            text = "UNLOCKED",
                            fontSize = 12.sp,
                            color = Color.Green,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(
                                    Color.Green.copy(alpha = 0.1f),
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    else -> {
                        borderData.pointsAway?.let { points ->
                            Text(
                                text = "$points POINTS AWAY",
                                fontSize = 12.sp,
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .background(
                                        Color.Red.copy(alpha = 0.1f),
                                        RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            // Action Button
            when {
                borderData.isCurrent -> {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color.Green,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Current",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                borderData.isUnlocked -> {
                    Button(
                        onClick = onApplyClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray.copy(alpha = 0.2f),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text(
                            text = "Apply",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                else -> {
                    Text(
                        text = "Locked",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BorderItemPreview() {
    Column {
        BorderItem(
            borderData = BorderItemData(
                name = "Bronze Border",
                level = "Starter border",
                points = 0,
                isUnlocked = true,
                isCurrent = false,
                borderColor = Color(0xFFCD7F32)
            )
        )

        BorderItem(
            borderData = BorderItemData(
                name = "Gold Border",
                level = "Gold level",
                points = 1500,
                isUnlocked = true,
                isCurrent = true,
                borderColor = Color(0xFFFFD700)
            )
        )

        BorderItem(
            borderData = BorderItemData(
                name = "Platinum Border",
                level = "Platinum level",
                points = 3000,
                isUnlocked = false,
                isCurrent = false,
                borderColor = Color(0xFFE5E4E2),
                pointsAway = 850
            )
        )
    }
}