package com.example.tobaguide.presentation.component.reward

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.data.DummyData


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header() {

    val leaderboardData = remember { DummyData.getLeaderboardData() }
    var selectedTab by remember { mutableStateOf("MONTHLY") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp, bottom = 0.dp)
    ) {
        Text(
            text = "LEADERBOARD",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, top = 4.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = "Top performers this month",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        TopThreePodium(leaderboardData.topThree)
        Spacer(modifier = Modifier.height(24.dp))
    }
}