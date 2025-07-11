package com.example.tobaguide.presentation.component.itinerary.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.presentation.screens.itinerary.ItineraryActivity
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BekalItem(
    icon: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF2E7D32)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow",
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )
        }
    }
}

private fun formatCurrency(amount: Int): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    return formatter.format(amount).replace("IDR", "Rp")
}

@Preview(showBackground = true)
@Composable
fun ItineraryHeaderPreview() {
    MaterialTheme {
        ItineraryHeader(
            title = "Hari 1",
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItineraryDateCardPreview() {
    MaterialTheme {
        ItineraryDateCard(
            date = "Sabtu 10 Mei 2025",
            activities = listOf(
                ItineraryActivity(
                    time = "08:00 - 10:00",
                    title = "Explore Wisata Alam Batu Gantung",
                    price = 45000
                ),
                ItineraryActivity(
                    time = "10:20 - 11:30",
                    title = "Explore Wisata Bahari Pantai Bebas",
                    price = 20000
                ),
                ItineraryActivity(
                    time = "13:00 - 15:00",
                    title = "Explore Wisata Alam Bukit Holbung",
                    price = 35000
                )
            ),
            travelerCount = 2,
            totalPrice = 200000,
            onEditClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BekalPerjalananSectionPreview() {
    MaterialTheme {
        BekalPerjalananSection(
            onCekPetaPerjalananClick = {},
            onAkomodasiClick = {}
        )
    }
}