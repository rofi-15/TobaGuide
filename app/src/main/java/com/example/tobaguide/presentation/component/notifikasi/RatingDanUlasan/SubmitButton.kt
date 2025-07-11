package com.example.tobaguide.presentation.component.notifikasi.RatingDanUlasan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubmitButton(
    text: String = "Kirim",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2E7D32)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingComponentsPreview() {
    Column {
        RatingHeader()
        Spacer(modifier = Modifier.height(16.dp))
        UserInfoCard()
        Spacer(modifier = Modifier.height(16.dp))
        RatingQuestion()
        Spacer(modifier = Modifier.height(16.dp))
        EmojiRating(selectedRating = 3)
        Spacer(modifier = Modifier.height(16.dp))
        StarRating(rating = 3)
        Spacer(modifier = Modifier.height(16.dp))
        ReviewPrompt()
        Spacer(modifier = Modifier.height(16.dp))
        SubmitButton()
    }
}