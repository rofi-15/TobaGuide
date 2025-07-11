package com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R

@Composable
fun AtasHome(
    onNotificationClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Explore",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.Black
            )
            Text(
                text = "Danau Toba!",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.Black
            )
        }

        Image(
            painter = painterResource(id = R.drawable.lonceng),
            contentDescription = "notification icon",
            modifier = Modifier
                .size(26.dp)
                .clickable { onNotificationClick() } // Tambahkan clickable untuk navigasi
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AtasHomePreview() {
    MaterialTheme {
        Surface(
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            AtasHome(
                onNotificationClick = {
                    println("Preview: Notification clicked")
                }
            )
        }
    }
}