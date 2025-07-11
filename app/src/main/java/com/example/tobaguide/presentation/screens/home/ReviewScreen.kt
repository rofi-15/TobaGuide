package com.example.tobaguide.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.R
import com.example.tobaguide.presentation.component.home.Review.RatingOverview
import com.example.tobaguide.presentation.component.home.Review.ReviewData
import com.example.tobaguide.presentation.component.home.Review.ReviewHeader
import com.example.tobaguide.presentation.component.home.Review.ReviewItem

@Composable
fun ReviewScreen(
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val sampleReviews = listOf(
        ReviewData(
            userName = "Bedol ni bos",
            rating = 4.5f,
            daysAgo = 4,
            reviewText = "Tempatnya indah nyaman dan sejuk Pemandangan alam berupa bukit hijau dan danau yang jernih. Semoga tetap terawat bersih",
            userImageRes = R.drawable.profil1
        ),
        ReviewData(
            userName = "Rofi nibosku",
            rating = 4.5f,
            daysAgo = 5,
            reviewText = "PAROPO DAN TAO SILALAHI lokasi wisata yang ada terletak tidak terlalu jauh sepanjang pantai desa Silalahi I, Silalahi II, Silalahi III, dan Paropo",
            userImageRes = R.drawable.rofii
        ),
        ReviewData(
            userName = "Kotekkkkkkk",
            rating = 4.5f,
            daysAgo = 6,
            reviewText = "Paropo terletak di tepian danau toba.Tempatnya bagus simpel indah ada air danauny masih sangat bersih",
            userImageRes = R.drawable.kotek
        ),
        ReviewData(
            userName = "Courtney Henry",
            rating = 4.5f,
            daysAgo = 7,
            reviewText = "Tempatnya indah nyaman dan sejuk Pemandangan alam berupa bukit hijau dan danau yang jernih. Semoga tetap terjaga bersih",
            userImageRes = R.drawable.profil1
        ),
        ReviewData(
            userName = "Wade Warren",
            rating = 4.0f,
            daysAgo = 8,
            reviewText = "Lokasi yang sangat bagus untuk bersantai dan menikmati pemandangan Danau Toba. Air danau yang jernih dan udara yang sejuk membuat betah berlama-lama disini.",
            userImageRes = R.drawable.profil1
        ),
        ReviewData(
            userName = "Esther Howard",
            rating = 5.0f,
            daysAgo = 9,
            reviewText = "Tempat wisata yang wajib dikunjungi! Pemandangannya luar biasa indah, fasilitas cukup lengkap dan harga tiket masuk yang terjangkau. Recommended banget!",
            userImageRes = R.drawable.profil1
        )
    )
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ReviewHeader(
                onBackClick = onBackClick
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // MEMBUAT BISA SCROLL
            ) {
                // RATING OVERVIEW
                RatingOverview(
                    rating = 4.5f,
                    totalReviews = sampleReviews.size
                )

                Spacer(modifier = Modifier.height(16.dp))

                sampleReviews.forEach { review ->
                    ReviewItem(
                        review = review,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    ReviewScreen(
        onBackClick = {
            println("Preview: Back button clicked")
        }
    )
}