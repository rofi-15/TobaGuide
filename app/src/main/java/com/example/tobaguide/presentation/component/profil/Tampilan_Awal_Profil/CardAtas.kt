package com.example.tobaguide.presentation.component.profil.Tampilan_Awal_Profil

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun profil(
    onAvatarClick: () -> Unit = {}
) {
    Box(
    modifier = Modifier.padding(top = 16.dp)
    ){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profil Saya",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F4FD))
                    .border(4.dp, Color(0xFFFFB000), CircleShape)
                    .clickable { onAvatarClick() },
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(90.dp)) {
                    val center = Offset(size.width / 2, size.height / 2)
                    val personColor = Color(0xFF4A90E2)

                    // Draw head (larger, better proportioned)
                    val headRadius = size.width * 0.18f
                    val headCenter = Offset(center.x, center.y - size.height * 0.22f)
                    drawCircle(
                        color = personColor,
                        radius = headRadius,
                        center = headCenter
                    )

                    // Draw neck (small rectangle)
                    val neckWidth = size.width * 0.08f
                    val neckHeight = size.height * 0.12f
                    drawRect(
                        color = personColor,
                        topLeft = Offset(
                            center.x - neckWidth / 2,
                            headCenter.y + headRadius - size.height * 0.02f
                        ),
                        size = androidx.compose.ui.geometry.Size(neckWidth, neckHeight)
                    )

                    val shoulderWidth = size.width * 0.65f
                    val shoulderHeight = size.height * 0.35f
                    val shoulderTop = center.y + size.height * 0.08f

                    drawRoundRect(
                        color = personColor,
                        topLeft = Offset(center.x - shoulderWidth / 2, shoulderTop),
                        size = androidx.compose.ui.geometry.Size(shoulderWidth, shoulderHeight),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(
                            size.width * 0.15f,
                            size.width * 0.15f
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Name
            Text(
                text = "Roji Fahrofi",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            // Subtitle
            Text(
                text = "Petualang Tanjung Morawa",
                color = Color(0xFF666666),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp),
                textAlign = TextAlign.Center
            )

            // Achievement Points Row (Tetap dipertahankan)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AchievementPoint("30", true)
                ConnectorDots()
                AchievementPoint("100", true)
                ConnectorDots()
                AchievementPoint("200", false)
                ConnectorDots()
                AchievementPoint("300", false)
                ConnectorDots()
                AchievementPoint("350", false)
            }
        }
    }
    }
}

@Composable
fun AchievementPoint(points: String, achieved: Boolean) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(if (achieved) Color(0xFFFFB000) else Color.White)
            .border(
                2.dp,
                if (achieved) Color(0xFFFFB000) else Color(0xFFCCCCCC),
                CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = points,
            color = if (achieved) Color.White else Color(0xFF999999),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ConnectorDots() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(3.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFCCCCCC))
            )
        }
    }
}

// Simple extension functions for dotted circle
fun DrawScope.drawDottedCircle(
    center: Offset,
    radius: Float,
    color: Color,
    strokeWidth: Float,
    dotCount: Int
) {
    val angleStep = 360f / dotCount
    val dotSize = strokeWidth * 0.8f

    for (i in 0 until dotCount) {
        val angle = Math.toRadians((i * angleStep).toDouble())
        val x = center.x + radius * cos(angle).toFloat()
        val y = center.y + radius * sin(angle).toFloat()

        drawCircle(
            color = color,
            radius = dotSize / 2,
            center = Offset(x, y)
        )
    }
}

fun DrawScope.drawDottedArc(
    center: Offset,
    radius: Float,
    startAngle: Float,
    sweepAngle: Float,
    color: Color,
    strokeWidth: Float,
    dotCount: Int
) {
    val angleStep = sweepAngle / dotCount
    val dotSize = strokeWidth * 0.8f

    for (i in 0 until dotCount) {
        val angle = Math.toRadians((startAngle + i * angleStep).toDouble())
        val x = center.x + radius * cos(angle).toFloat()
        val y = center.y + radius * sin(angle).toFloat()

        drawCircle(
            color = color,
            radius = dotSize / 2,
            center = Offset(x, y)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(vertical = 16.dp)
    ) {
        profil(
            onAvatarClick = {
                println("Avatar clicked - Navigate to Profile Border")
            }
        )
    }
}