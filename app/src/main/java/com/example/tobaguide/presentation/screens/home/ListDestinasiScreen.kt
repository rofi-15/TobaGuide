package com.example.tobaguide.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.home.List_Wisata.SearchHeader
import com.example.tobaguide.presentation.component.home.List_Wisata.GridRecommendationSection

@Composable
fun SearchScreen(
    onNavigateToDetail: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchHeader(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            GridRecommendationSection(
                searchQuery = searchQuery,
                onItemClick = onNavigateToDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        onNavigateToDetail = { destinationId ->
            println("Preview: Navigate to detail $destinationId")
        },
        onBackClick = {
            println("Preview: Back clicked")
        }
    )
}