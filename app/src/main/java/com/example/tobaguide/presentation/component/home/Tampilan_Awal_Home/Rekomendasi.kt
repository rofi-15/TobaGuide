package com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R

@Composable
fun Rekomendasi(
    onSeeAllClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rekomendasi",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Text(
                text = "Lihat semua >",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RecommendationCard(
                imageRes = R.drawable.image_1_2,
                title = "Desa Paropo",
                rating = "5.0",
                destinationId = "desa_paropo",
                modifier = Modifier.weight(1f),
                onClick = onItemClick
            )
            RecommendationCard(
                imageRes = R.drawable.image_efrata,
                title = "Air Terjun Efrata",
                rating = "4.7",
                destinationId = "air_terjun_efrata",
                modifier = Modifier.weight(1f),
                onClick = onItemClick
            )
        }
    }
}

@Composable
private fun RecommendationCard(
    imageRes: Int,
    title: String,
    rating: String,
    destinationId: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .height(252.dp)
            .clickable { onClick(destinationId) },
        shape = RoundedCornerShape(12.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
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
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bintang),
                        contentDescription = "Rating",
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = rating,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}