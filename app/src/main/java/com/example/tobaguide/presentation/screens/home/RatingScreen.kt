package com.example.tobaguide.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun RatingScreen(
    onBackClick: () -> Unit = {},
    onSubmitRating: (Int, String) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    var currentRating by remember { mutableIntStateOf(0) }
    var reviewText by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Header
            RatingHeader(
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            // User Info Card
            UserInfoCard()

            Spacer(modifier = Modifier.height(24.dp))

            // Rating question
            Text(
                text = "Bagaimana perjalanan kamu ?",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            EmojiRatingRow(
                selectedRating = currentRating,
                onRatingSelected = { rating ->
                    currentRating = rating
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            StarRatingRow(
                rating = currentRating,
                onRatingChanged = { rating ->
                    currentRating = rating
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Ketik ulasanmu yuk!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = {
                    Text(
                        text = "Tulis pengalaman perjalananmu...",
                        color = Color.Gray
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5)
                ),
                shape = RoundedCornerShape(12.dp),
                maxLines = 4
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(
                onClick = {
                    showSuccessDialog = true
                    onSubmitRating(currentRating, reviewText)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E7D5E)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Kirim",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Success Dialog
        if (showSuccessDialog) {
            SuccessDialog(
                onDismiss = { showSuccessDialog = false }
            )
        }
    }
}

@Composable
fun RatingHeader(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        Text(
            text = "Rating & Ulasan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        // Empty space for balance
        Spacer(modifier = Modifier.width(48.dp))
    }
}

@Composable
fun UserInfoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 4.dp), // Shadow offset
        colors = CardDefaults.cardColors(containerColor = Color(0x20000000)), // Shadow color
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-4).dp), // Main card offset
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Gift icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFFF9800), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🎁", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Misi baru!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = "Yuk dapatkan point dengan memberi ulasan",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "400+",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF9800)
                )
            }
        }
    }
}

@Composable
fun EmojiRatingRow(
    selectedRating: Int,
    onRatingSelected: (Int) -> Unit
) {
    val emojis = listOf("😢", "😟", "😐", "😊", "🤩")
    val currentEmoji = when(selectedRating) {
        1 -> "😢"
        2 -> "😟"
        3 -> "😐"
        4 -> "😊"
        5 -> "🤩"
        else -> "😐"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = currentEmoji,
            fontSize = 120.sp
        )
    }
}

@Composable
fun StarRatingRow(
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5) { index ->
            val starIndex = index + 1
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star $starIndex",
                tint = if (starIndex <= rating) Color(0xFFFFD700) else Color(0xFFE0E0E0),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onRatingChanged(starIndex) }
                    .padding(horizontal = 4.dp)
            )
        }
    }
}

@Composable
fun SuccessDialog(
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Success icon with sunglasses emoji
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF2E7D5E), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "😎",
                        fontSize = 40.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "MASUK, BOSKU!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Poin kamu bertambah!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Semoga perjalanan\nselanjutnya makin asyik!",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "+400 Poin",
                    fontSize = 14.sp,
                    color = Color(0xFFFF9800),
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D5E)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Oke ya!",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingScreenPreview() {
    RatingScreen(
        onBackClick = {
            println("Back clicked")
        },
        onSubmitRating = { rating, review ->
            println("Rating submitted: $rating stars, Review: $review")
        }
    )
}