package com.example.tobaguide.presentation.component.home.List_Wisata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R
import com.example.tobaguide.data.DummyData.destinations

@Composable
fun GridRecommendationSection(
    searchQuery: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredDestinations = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            destinations
        } else {
            destinations.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    val destinationPairs = remember(filteredDestinations) {
        filteredDestinations.chunked(2)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Rekomendasi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.Black
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = destinationPairs,
                key = { pair -> pair.firstOrNull()?.id ?: "" }
            ) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    GridDestinationCard(
                        destination = pair[0],
                        onItemClick = onItemClick,
                        modifier = Modifier.weight(1f)
                    )
                    if (pair.size > 1) {
                        GridDestinationCard(
                            destination = pair[1],
                            onItemClick = onItemClick,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun GridDestinationCard(
    destination: DestinationItem,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageRes = when (destination.imageKey) {
        "desa_paropo" -> R.drawable.image1
        "air_terjun_efrata" -> R.drawable.image2
        "batu_gantung" -> R.drawable.image3
        "bukit_cinta" -> R.drawable.image4
        "pantai_bebas" -> R.drawable.image1
        "danau_toba" -> R.drawable.image2
        "pulau_samosir" -> R.drawable.image3
        "hot_spring" -> R.drawable.image4
        "desa_paropi" -> R.drawable.image1
        "air_terjun_efrati" -> R.drawable.image2
        "batu_gantungg" -> R.drawable.image3
        "bukit_cintau" -> R.drawable.image4
        "pantai_bebass" -> R.drawable.image1
        "danau_tobaa" -> R.drawable.image2
        "pulau_samosiri" -> R.drawable.image3
        "hot_springing" -> R.drawable.image4
        else -> android.R.drawable.ic_menu_gallery
    }

    Card(
        modifier = modifier
            .height(252.dp)
            .clickable { onItemClick(destination.id) },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = destination.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(252.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(252.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = destination.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = destination.rating,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridRecommendationSectionPreview() {
    GridRecommendationSection(
        searchQuery = "",
        onItemClick = {}
    )
}