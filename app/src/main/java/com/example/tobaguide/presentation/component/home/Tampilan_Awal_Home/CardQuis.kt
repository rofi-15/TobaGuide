package com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.tobaguide.R
import com.example.tobaguide.domain.model.Quis

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)

@Composable
fun CardQuis() {
    var showQuizDialog by remember { mutableStateOf(false) }
    var selectedQuiz by remember { mutableStateOf<Quis?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Dapatkan Pointmu disini hari ini !",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))

        val quizItems = listOf(
            Quis("Kuis Harian", "10 Pertanyaan acak", R.drawable.quiz),
            Quis("Kuis Budaya", "10 Perjalana Baru", R.drawable.quiz),
            Quis("Kuis Wisata", "10 Cek Aktivitas Baru", R.drawable.quiz)
        )

        LazyRow(
            contentPadding = PaddingValues(end = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(quizItems) { quiz ->
                QuizCard(
                    quis = quiz,
                    onClick = {
                        selectedQuiz = quiz
                        showQuizDialog = true
                    }
                )
            }
        }
    }

    if (showQuizDialog && selectedQuiz != null) {
        QuizDialog(
            quiz = selectedQuiz!!,
            onDismiss = {
                showQuizDialog = false
                selectedQuiz = null
            }
        )
    }
}

@Composable
fun QuizCard(
    quis: Quis,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .padding(end = 12.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D2D2D) // Dark card background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        Color(0xFFFFB800), // Yellow background for icon
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = quis.image),
                    contentDescription = quis.title,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = quis.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // White text for better contrast
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = quis.description,
                    fontSize = 14.sp,
                    color = Color(0xFFB0B0B0) // Light gray for description
                )
            }
        }
    }
}

@Composable
fun QuizDialog(
    quiz: Quis,
    onDismiss: () -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(-1) }
    var showAnswer by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }
    var quizCompleted by remember { mutableStateOf(false) }

    val questions = remember {
        when (quiz.title) {
            "Kuis Harian" -> listOf(
                QuizQuestion(
                    "Danau Toba terbentuk dari apa?",
                    listOf("A. Meteorit", "B. Letusan gunung berapi purba", "C. Gerakan tektonik", "D. Erosi air"),
                    1
                ),
                QuizQuestion(
                    "Pulau samosir terletak di tengah?",
                    listOf("A. Danau Sentani", "B. Danau Singkarak", "C. Danau Toba", "D. Danau Maninjau"),
                    2
                )
            )
            else -> listOf(
                QuizQuestion(
                    "Danau Toba terbentuk dari apa?",
                    listOf("A. Meteorit", "B. Letusan gunung berapi purba", "C. Gerakan tektonik", "D. Erosi air"),
                    1
                )
            )
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                if (quizCompleted) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(
                                        Color.Gray.copy(alpha = 0.2f),
                                        CircleShape
                                    )
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(
                                    Color(0xFFFFB800),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🏆",
                                fontSize = 40.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    Color(0xFFFFB800),
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "+5",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Selamat!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Kamu telah menyelesaikan kuis harian, sampai jumpa besok!",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1B5E5E)
                            )
                        ) {
                            Text(
                                text = "Keluar",
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF1B5E5E))
                                .padding(16.dp)
                        ) {
                            Text(
                                text = quiz.title,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)
                            )

                            IconButton(
                                onClick = onDismiss,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .size(24.dp)
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Column(
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Text(
                                text = "Pertanyaan ${currentQuestionIndex + 1} dari ${questions.size}",
                                fontSize = 14.sp,
                                color = Color(0xFFFF8C00),
                                fontWeight = FontWeight.Medium
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .background(Color.Gray.copy(alpha = 0.2f))
                                    .padding(vertical = 8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(
                                            fraction = (currentQuestionIndex + 1).toFloat() / questions.size
                                        )
                                        .height(4.dp)
                                        .background(Color(0xFFFF8C00))
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = questions[currentQuestionIndex].question,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                lineHeight = 24.sp
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            questions[currentQuestionIndex].options.forEachIndexed { index, option ->
                                val isSelected = selectedAnswer == index
                                val isCorrect = index == questions[currentQuestionIndex].correctAnswer
                                val showCorrectAnswer = showAnswer && isCorrect

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clickable {
                                            if (!showAnswer) {
                                                selectedAnswer = index
                                            }
                                        },
                                    colors = CardDefaults.cardColors(
                                        containerColor = when {
                                            showCorrectAnswer -> Color(0xFFFF8C00)
                                            isSelected && !showAnswer -> Color(0xFFFF8C00)
                                            else -> Color(0xFFF5F5F5)
                                        }
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = if (isSelected || showCorrectAnswer) 2.dp else 0.dp
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = option,
                                            fontSize = 16.sp,
                                            color = if (isSelected || showCorrectAnswer) Color.White else Color.Black,
                                            modifier = Modifier.weight(1f)
                                        )

                                        if (showCorrectAnswer) {
                                            Icon(
                                                Icons.Default.CheckCircle,
                                                contentDescription = "Correct",
                                                tint = Color.White,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            if (showAnswer) {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFFE3F2FD)
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Tahukah Anda?",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF1976D2)
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = when (currentQuestionIndex) {
                                                0 -> "Danau Toba adalah danau vulkanik."
                                                1 -> "Pulau Samosir adalah pulau vulkanik di tengah Danau Toba dengan luas 630 km²."
                                                else -> "Informasi menarik tentang pertanyaan ini."
                                            },
                                            fontSize = 12.sp,
                                            color = Color(0xFF1976D2)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }

                            // Action button
                            Button(
                                onClick = {
                                    if (!showAnswer && selectedAnswer != -1) {
                                        showAnswer = true
                                        if (selectedAnswer == questions[currentQuestionIndex].correctAnswer) {
                                            score++
                                        }
                                    } else if (showAnswer) {
                                        if (currentQuestionIndex < questions.size - 1) {
                                            currentQuestionIndex++
                                            selectedAnswer = -1
                                            showAnswer = false
                                        } else {
                                            quizCompleted = true
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF1B5E5E)
                                ),
                                enabled = selectedAnswer != -1
                            ) {
                                Text(
                                    text = if (!showAnswer) "Lanjutkan" else if (currentQuestionIndex < questions.size - 1) "Lanjutkan" else "Selesai",
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardQuisPreview() {
    CardQuis()
}