package com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class RecommendationItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val iconColor: Color
)

@Composable
fun RecommendationSection(
    modifier: Modifier = Modifier
) {
    val recommendations = listOf(
        RecommendationItem(
            title = "Restoran Terdekat",
            subtitle = "5 restoran dalam 2 km",
            icon = Icons.Default.Restaurant,
            iconColor = Color(0xFFFF6B35)
        ),
        RecommendationItem(
            title = "Hotel & Penginapan",
            subtitle = "8 hotel tersedia",
            icon = Icons.Default.Hotel,
            iconColor = Color(0xFF4CAF50)
        ),
        RecommendationItem(
            title = "Tempat Wisata",
            subtitle = "12 destinasi menarik",
            icon = Icons.Default.Attractions,
            iconColor = Color(0xFF2196F3)
        ),
        RecommendationItem(
            title = "ATM & Bank",
            subtitle = "3 ATM terdekat",
            icon = Icons.Default.AccountBalance,
            iconColor = Color(0xFF9C27B0)
        ),
        RecommendationItem(
            title = "Transportasi",
            subtitle = "Terminal & rental",
            icon = Icons.Default.DirectionsCar,
            iconColor = Color(0xFFFFC107)
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rekomendasi Sekitar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            TextButton(
                onClick = { /* Handle see all recommendations */ }
            ) {
                Text(
                    text = "Lihat Semua",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Recommendations list
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 0.dp)
        ) {
            items(recommendations) { recommendation ->
                RecommendationCard(
                    recommendation = recommendation,
                    onClick = { /* Handle recommendation click */ }
                )
            }
        }
    }
}

@Composable
private fun RecommendationCard(
    recommendation: RecommendationItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon container
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(recommendation.iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = recommendation.icon,
                    contentDescription = recommendation.title,
                    tint = recommendation.iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = recommendation.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Subtitle
            Text(
                text = recommendation.subtitle,
                fontSize = 11.sp,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}