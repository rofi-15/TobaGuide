package com.example.tobaguide.presentation.component.itinerary.detail.akomodasi

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.presentation.screens.itinerary.AccommodationItem

@Composable
fun AccommodationCheckItem(
    text: String,
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF2E7D32),
                uncheckedColor = Color.Gray
            ),
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccommodationHeaderPreview() {
    MaterialTheme {
        AccommodationHeader(
            title = "Daftar Akomodasi",
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccommodationCardPreview() {
    MaterialTheme {
        AccommodationCard(
            accommodationItem = AccommodationItem(
                id = 1,
                items = listOf(
                    "popme ayam bawang",
                    "powerbank & charger",
                    "perlengkapan mandi"
                )
            ),
            onEdit = {},
            onDelete = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccommodationCheckItemPreview() {
    MaterialTheme {
        AccommodationCheckItem(
            text = "popme ayam bawang"
        )
    }
}