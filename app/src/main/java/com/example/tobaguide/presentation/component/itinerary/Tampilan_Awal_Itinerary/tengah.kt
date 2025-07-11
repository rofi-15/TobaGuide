package com.example.tobaguide.presentation.component.itinerary.Tampilan_Awal_Itinerary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

// Tengah sekarang menjadi komponen yang sangat sederhana
@Composable
fun Tengah() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Kamu belum memiliki Itinerary Apa pun",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}