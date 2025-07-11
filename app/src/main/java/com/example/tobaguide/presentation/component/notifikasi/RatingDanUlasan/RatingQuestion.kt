package com.example.tobaguide.presentation.component.notifikasi.RatingDanUlasan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RatingQuestionText(
    question: String = "Bagaimana perjalanan kamu bersama Toba Guide?",
    modifier: Modifier = Modifier
) {
    Text(
        text = question,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        lineHeight = 24.sp
    )
}

