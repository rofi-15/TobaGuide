package com.example.tobaguide.presentation.component.Awal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Text(
    title: String = "Keindahan\nDalam Cekungan",
    subtitle: String = "Rasakan dari ketinggian dahsyat Gunung Toba,\nmenikmati Danau Toba, terbesar se Asia Tenggara",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            ),
            color = Color.White,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                lineHeight = 18.sp
            ),
            color = Color.White.copy(alpha = 0.9f),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun TextPreview() {
    Text()
}