package com.example.tobaguide.presentation.screens.home

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
import com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home.AtasHome
import com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home.CardQuis
import com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home.DraggableChatbotContainer
import com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home.PointsCard
import com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home.Rekomendasi

@Composable
fun HomeScreen(
    onManualClick: () -> Unit = {},
    onAIClick: () -> Unit = {},
    onSeeAllRecommendationsClick: () -> Unit = {},
    onRecommendationItemClick: (String) -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onChatbotAboutClick: () -> Unit = {},
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

    // Spacing yang responsif
    val componentSpacing = when {
        screenHeight < 600.dp -> 8.dp
        screenHeight < 700.dp -> 12.dp
        else -> 16.dp
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    top = verticalPadding,
                    start = horizontalPadding,
                    end = horizontalPadding,
                    bottom = 100.dp // Extra space untuk chatbot dan bottom navigation
                ),
            verticalArrangement = Arrangement.spacedBy(componentSpacing)
        ) {
            // Header Section
            AtasHome(
                onNotificationClick = onNotificationClick
            )

            // Points Card Section
            PointsCard()

            // Quiz Card Section
            CardQuis()

            // Recommendations Section
            Rekomendasi(
                onSeeAllClick = onSeeAllRecommendationsClick,
                onItemClick = onRecommendationItemClick
            )

            // Extra space di bawah untuk memastikan konten tidak tertutup
            Spacer(modifier = Modifier.height(20.dp))
        }

        // Draggable Chatbot - Always on top
        DraggableChatbotContainer(
            onAboutClick = onChatbotAboutClick
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenSmallPreview() {
    MaterialTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun HomeScreenMediumPreview() {
    MaterialTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, widthDp = 480, heightDp = 900)
@Composable
fun HomeScreenLargePreview() {
    MaterialTheme {
        HomeScreen()
    }
}