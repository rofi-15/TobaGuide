package com.example.tobaguide.presentation.screens.itinerary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.itinerary.detail.ItineraryHeader
import com.example.tobaguide.presentation.component.itinerary.detail.ItineraryDateCard
import com.example.tobaguide.presentation.component.itinerary.detail.BekalPerjalananSection

@Composable
fun ItineraryDetailScreen(
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onCekPetaPerjalananClick: () -> Unit = {},
    onAkomodasiClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    // Responsive padding berdasarkan ukuran layar
    val horizontalPadding = when {
        screenWidth < 360.dp -> 16.dp
        screenWidth < 400.dp -> 20.dp
        else -> 24.dp
    }

    val verticalPadding = when {
        screenHeight < 600.dp -> 8.dp
        screenHeight < 700.dp -> 12.dp
        else -> 16.dp
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    top = verticalPadding,
                    start = horizontalPadding,
                    end = horizontalPadding,
                    bottom = verticalPadding
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Section
            ItineraryHeader(
                title = "Hari 1",
                onBackClick = onBackClick
            )

            // Date Card Section
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
                onEditClick = onEditClick
            )

            // Bekal Perjalanan Section
            BekalPerjalananSection(
                onCekPetaPerjalananClick = onCekPetaPerjalananClick,
                onAkomodasiClick = onAkomodasiClick
            )
        }
    }
}

data class ItineraryActivity(
    val time: String,
    val title: String,
    val price: Int
)

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun ItineraryDetailScreenPreview() {
    MaterialTheme {
        ItineraryDetailScreen()
    }
}