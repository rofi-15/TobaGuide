package com.example.tobaguide.presentation.screens.itinerary.manual

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.domain.model.LocationDetail
import com.example.tobaguide.domain.model.LocationPhoto
import com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.maps.MapSection
import com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.maps.PhotoSection
import com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.maps.RecommendationSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    location: LocationDetail,

    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {

        TopAppBar(
            title = {
                Text(
                    text = location.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                MapSection(location = location)
            }

            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Detail Informasi",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Lokasi wisata populer di Danau Toba",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal
                        )
                    }


                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Gray.copy(alpha = 0.3f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    PhotoSection(photos = location.photos)

                    Spacer(modifier = Modifier.height(16.dp))


                    RecommendationSection()

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }


            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LocationDetailScreenPreview() {
    val sampleLocation = LocationDetail(
        name = "Danau Toba",
        latitude = 2.6845,
        longitude = 98.8756,
        photos = listOf(
            LocationPhoto("1", ""),
            LocationPhoto("2", ""),
            LocationPhoto("3", ""),
            LocationPhoto("4", ""),
            LocationPhoto("5", ""),
            LocationPhoto("6", "")
        ),
        hasWarning = true
    )

    LocationDetailScreen(
        location = sampleLocation,

    )
}