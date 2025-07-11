package com.example.tobaguide.presentation.component.itinerary.detail


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R

@Composable
fun BekalPerjalananSection(
    onCekPetaPerjalananClick: () -> Unit = {},
    onAkomodasiClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Bekal Perjalanan",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))
        BekalItem(
            icon = R.drawable.map,
            title = "Cek Peta Perjalanan",
            subtitle = "List map buat gak nyasar",
            onClick = onCekPetaPerjalananClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        BekalItem(
            icon = R.drawable.akomodasi,
            title = "Akomodasi",
            subtitle = "Catat sebutankan yuk!",
            onClick = onAkomodasiClick
        )
    }
}
