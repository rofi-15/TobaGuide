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
fun EmojiRating(
    selectedRating: Int = 0,
    onRatingSelected: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val emojis = listOf("😔", "😞", "😊", "😘", "😍")
    val emojiColors = listOf(
        Color(0xFFE0E0E0), // Gray for sad
        Color(0xFFFFB74D), // Orange for disappointed
        Color(0xFFFFB74D), // Orange for happy
        Color(0xFFFFB74D), // Orange for kiss
        Color(0xFFE91E63)  // Pink for love
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(emojiColors.getOrElse(selectedRating - 1) { Color(0xFFE0E0E0) }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emojis.getOrElse(selectedRating - 1) { "😔" },
                fontSize = 48.sp
            )
        }
    }
}
