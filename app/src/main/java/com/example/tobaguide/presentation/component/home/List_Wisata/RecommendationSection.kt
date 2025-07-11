package com.example.tobaguide.presentation.component.home.List_Wisata

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecommendationSection(
    searchQuery: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val destinations = remember {
        listOf(
            DestinationItem("1", "Desa Paropo", "4.1", "desa_paropo"),
            DestinationItem("2", "Air Terjun Efrata", "4.6", "air_terjun_efrata"),
            DestinationItem("3", "Batu Gantung", "4.2", "batu_gantung"),
            DestinationItem("4", "Bukit Cinta", "4.3", "bukit_cinta"),
            DestinationItem("5", "Pantai Bebas", "4.5", "pantai_bebas"),
            DestinationItem("6", "Danau Toba", "4.8", "danau_toba")
        )
    }

    // Filter destinations based on search query
    val filteredDestinations = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            destinations
        } else {
            destinations.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Rekomendasi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = filteredDestinations,
                key = { it.id }
            ) { destination ->
                DestinationCard(
                    destination = destination,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

data class DestinationItem(
    val id: String,
    val name: String,
    val rating: String,
    val imageKey: String
)

@Preview(showBackground = true)
@Composable
fun RecommendationSectionPreview() {
    RecommendationSection(
        searchQuery = "",
        onItemClick = {}
    )
}