package com.example.tobaguide.presentation.component.home.List_Wisata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun DestinationCard(
    destination: DestinationItem,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageRes = when (destination.imageKey) {
        "desa_paropo" -> android.R.drawable.ic_menu_gallery
        "air_terjun_efrata" -> android.R.drawable.ic_menu_gallery
        "batu_gantung" -> android.R.drawable.ic_menu_gallery
        "bukit_cinta" -> android.R.drawable.ic_menu_gallery
        "pantai_bebas" -> android.R.drawable.ic_menu_gallery
        "danau_toba" -> android.R.drawable.ic_menu_gallery
        else -> android.R.drawable.ic_menu_gallery
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(destination.id) }
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(200.dp)
                .padding(end = 8.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = destination.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
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
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = destination.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
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
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DestinationCardPreview() {
    DestinationCard(
        destination = DestinationItem("1", "Desa Paropo", "4.1", "desa_paropo"),
        onItemClick = {}
    )
}