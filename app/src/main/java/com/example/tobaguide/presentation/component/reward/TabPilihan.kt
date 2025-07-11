package com.example.tobaguide.presentation.component.reward

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabPilihan(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    val tabs = listOf("WEEKLY", "MONTHLY", "ALL TIME")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tabs.forEach { tab ->
            val isSelected = tab == selectedTab
            Button(
                onClick = { onTabSelected(tab) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFF1B5E20) else Color.Transparent,
                    contentColor = if (isSelected) Color.White else Color.Gray
                ),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text = tab,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabPilihanPreview() {
    var selectedTab by remember { mutableStateOf("MONTHLY") }
    TabPilihan(
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it }
    )
}