package com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tobaguide.domain.model.LocationPhoto

@Composable
fun PhotoItem(
    photo: LocationPhoto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(120.dp, 90.dp)
            .clickable { },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Photo placeholder",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}
