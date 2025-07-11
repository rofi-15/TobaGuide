package com.example.tobaguide.presentation.component.notifikasi.RatingDanUlasan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StarRating(
    rating: Int = 0,
    onRatingChanged: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            val starIndex = index + 1
            val isSelected = starIndex <= rating

            IconButton(
                onClick = { onRatingChanged(starIndex) },
                modifier = Modifier.size(48.dp)
            ) {
                Text(
                    text = if (isSelected) "⭐" else "☆",
                    fontSize = 32.sp,
                    color = if (isSelected) Color(0xFFFFD700) else Color(0xFFE0E0E0)
                )
            }
        }
    }

    // Rating dots indicator
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(5) { index ->
            val dotIndex = index + 1
            val isSelected = dotIndex <= rating

            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color.Black else Color(0xFFE0E0E0)
                    )
            )
        }
    }
}