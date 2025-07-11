package com.example.tobaguide.presentation.screens.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.tobaguide.R
import com.example.tobaguide.domain.model.RecommendationItem
import com.example.tobaguide.presentation.component.home.detail_Wisata.Comment
import com.example.tobaguide.presentation.component.home.detail_Wisata.DetailComments
import com.example.tobaguide.presentation.component.home.detail_Wisata.DetailDescription
import com.example.tobaguide.presentation.component.home.detail_Wisata.DetailHeader
import com.example.tobaguide.presentation.component.home.detail_Wisata.DetailInfo
import com.example.tobaguide.presentation.component.home.detail_Wisata.DetailRating
import com.example.tobaguide.presentation.component.home.detail_Wisata.DetailRecommendation

@Composable
fun DetailScreen(
    destinationId: String,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToReview: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val images = listOf(
        R.drawable.image_1_4,
        R.drawable.image_1_2,
        R.drawable.image_1_3
    )

    val sampleComments = listOf(
        Comment(
            userName = "Bagus",
            rating = 5f,
            comment = "Tempatnya indah nyaman dan sejuk Pemandangan alam berupa bukit hijau dan danau yang jernih Semoga tetap terjaga bersih",
            timeAgo = "4 hari lalu"
        )
    )

    val sampleRecommendations = listOf(
        RecommendationItem(
            id = "bukit_gibeon",
            name = "Bukit Gibeon",
            imageRes = R.drawable.image1,
            rating = 4.5f
        ),
        RecommendationItem(
            id = "bukit_siallang",
            name = "Bukit Siallang",
            imageRes = R.drawable.image2,
            rating = 4.2f
        )
    )

    // Data untuk share berdasarkan destinationId
    val destinationData = getDestinationData(destinationId)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        DetailHeader(
            images = images,
            onBackClick = onBackClick,
            onShareClick = {
                shareDestination(
                    context = context,
                    title = destinationData.title,
                    location = destinationData.location,
                    description = destinationData.description
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(320.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(Color.White)
                    .zIndex(1f)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                DetailInfo(
                    title = destinationData.title,
                    location = destinationData.location
                )

                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                DetailDescription(
                    description = destinationData.description
                )

                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                DetailRating(
                    rating = 4.5f,
                    reviewCount = 127,
                    onSeeAllReviews = onNavigateToReview
                )

                DetailComments(
                    comments = sampleComments
                )

                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.2f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                DetailRecommendation(
                    recommendations = sampleRecommendations,
                    onItemClick = onNavigateToDetail
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// Data class untuk menyimpan informasi destinasi
private data class DestinationData(
    val title: String,
    val location: String,
    val description: String
)

// Function untuk mendapatkan data destinasi berdasarkan ID
private fun getDestinationData(destinationId: String): DestinationData {
    return when (destinationId) {
        "desa_paropo" -> DestinationData(
            title = "Desa Paropo",
            location = "Silahisabungan, Kabupaten Dairi, Sumatera Utara",
            description = "Desa ini terkenal dengan potensi wisata alamnya, khususnya pantai dan perbukitan yang menghadap Danau Toba."
        )
        "bukit_gibeon" -> DestinationData(
            title = "Bukit Gibeon",
            location = "Sumatera Utara",
            description = "Bukit dengan pemandangan indah dan udara yang sejuk."
        )
        "bukit_siallang" -> DestinationData(
            title = "Bukit Siallang",
            location = "Sumatera Utara",
            description = "Destinasi wisata alam dengan panorama yang menakjubkan."
        )
        else -> DestinationData(
            title = "Destinasi Wisata",
            location = "Sumatera Utara",
            description = "Temukan keindahan wisata Sumatera Utara."
        )
    }
}

// Function untuk handle sharing
private fun shareDestination(
    context: android.content.Context,
    title: String,
    location: String,
    description: String
) {
    val shareText = buildString {
        append("🏝️ $title\n\n")
        append("📍 $description\n\n")
        append("📌 Lokasi: $location\n\n")
        append("Temukan destinasi wisata menarik lainnya di TobaGuide! 🌟")
    }

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
        putExtra(Intent.EXTRA_SUBJECT, title)
    }

    context.startActivity(Intent.createChooser(shareIntent, "Bagikan melalui..."))
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        destinationId = "desa_paropo",
        onBackClick = { },
        onShareClick = { },
        onNavigateToDetail = { },
        onNavigateToReview = { }
    )
}