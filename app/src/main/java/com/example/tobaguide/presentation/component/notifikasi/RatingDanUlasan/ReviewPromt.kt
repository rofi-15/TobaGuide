package com.example.tobaguide.presentation.component.notifikasi.RatingDanUlasan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReviewPrompt(
    prompt: String = "Ketik ulasanmu yuk!",
    modifier: Modifier = Modifier
) {
    Text(
        text = prompt,
        fontSize = 16.sp,
        color = Color.Gray,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}