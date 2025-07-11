package com.example.tobaguide.presentation.component.notifikasi.RatingDanUlasan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R

@Composable
fun UserProfileSection(
    modifier: Modifier = Modifier,
    userName: String = "Misi baru!",
    userInfo: String = "Yuk dapatkan poin dengan memberi ulasan",
    points: String = "40k+"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Avatar
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFA500)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profil1), // Ganti dengan icon user Anda
                contentDescription = "User Avatar",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = userName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = userInfo,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Text(
            text = points,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFA500)
        )
    }
}

@Composable
fun RatingQuestion(
    modifier: Modifier = Modifier,
    question: String = "Bagaimana perjalanan kamu bersama Toba Guide?"
) {
    Text(
        text = question,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 24.dp)
    )
}