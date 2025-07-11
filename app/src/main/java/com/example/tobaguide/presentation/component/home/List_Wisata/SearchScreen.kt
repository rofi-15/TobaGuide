package com.example.tobaguide.presentation.component.home.List_Wisata

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchScreen(
    onNavigateToDetail: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchHeader(
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            onBackClick = { /* Handle back navigation */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        RecommendationSection(
            searchQuery = searchQuery,
            onItemClick = onNavigateToDetail
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        onNavigateToDetail = { destinationId ->
            println("Preview: Navigate to detail $destinationId")
        }
    )
}