package com.example.tobaguide.presentation.screens.itinerary

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.ItineraryCard
import com.example.tobaguide.presentation.component.itinerary.Tampilan_Awal_Itinerary.*
import com.example.tobaguide.presentation.viewmodel.ItineraryViewModel

@Composable
fun ItineraryScreen(
    onManualItineraryClick: () -> Unit,
    onAIItineraryClick: () -> Unit,
    onItineraryItemClick: (itineraryId: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItineraryViewModel = hiltViewModel()
) {
    val listState by viewModel.itineraryListState.collectAsState()
    var showSelectionDialog by remember { mutableStateOf(false) }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
        }
    }

    if (showSelectionDialog) {
        ItinerarySelectionDialog(
            onDismissRequest = { showSelectionDialog = false },
            onAiClick = onAIItineraryClick,
            onManualClick = onManualItineraryClick
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White, // Set background to white
        contentColor = MaterialTheme.colorScheme.onBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSelectionDialog = true },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, "Tambah Itinerary", tint = Color.White)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White) // Ensure column background is white
                .padding(top = 15.dp, start = 23.dp, end = 23.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ItineraryHeader(itineraries = listState.savedItineraries)
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.White), // Ensure box background is white
                contentAlignment = Alignment.Center
            ) {
                when {
                    listState.isLoading -> CircularProgressIndicator()
                    listState.savedItineraries.isNotEmpty() -> {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(listState.savedItineraries) { itinerary ->
                                ItineraryCard(
                                    itinerary = itinerary,
                                    onClick = { onItineraryItemClick(itinerary.id) },
                                    onDeleteClick = { viewModel.deleteItinerary(itinerary.id) }
                                )
                            }
                        }
                    }
                    else -> Tengah()
                }
            }
        }
    }
}