package com.example.tobaguide.presentation.screens.itinerary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.itinerary.detail.akomodasi.AccommodationHeader
import com.example.tobaguide.presentation.component.itinerary.detail.akomodasi.AccommodationCard

@Composable
fun AccommodationScreen(
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onEditAccommodation: (AccommodationItem) -> Unit = {},
    onDeleteAccommodation: (AccommodationItem) -> Unit = {},
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

    // Sample data with titles for better identification
    val accommodationItems = listOf(
        AccommodationItem(
            id = 1,
            title = "Barang yang harus dibawa:",
            items = listOf(
                "popme ayam bawang",
                "powerbank & charger",
                "perlengkapan mandi"
            )
        ),
        AccommodationItem(
            id = 2,
            title = "Catatan Tambahan:",
            items = listOf(
                "bawa uang tunai lebih untuk jaga-jaga",
                "siapkan KTP untuk registrasi"
            )
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
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
                    bottom = 100.dp // Space for FAB
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Section
            AccommodationHeader(
                title = "Daftar Akomodasi",
                onBackClick = onBackClick
            )

            // Accommodation Cards in Row Layout
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(accommodationItems) { accommodation ->
                    AccommodationCard(
                        accommodationItem = accommodation,
                        onEdit = { onEditAccommodation(accommodation) },
                        onDelete = { onDeleteAccommodation(accommodation) },
                        modifier = Modifier.width(280.dp) // Fixed width for consistent card size
                    )
                }
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFF2E7D32),
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Accommodation"
            )
        }
    }
}

data class AccommodationItem(
    val id: Int,
    val title: String = "",
    val items: List<String>
)

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun AccommodationScreenPreview() {
    MaterialTheme {
        AccommodationScreen()
    }
}