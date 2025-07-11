package com.example.tobaguide.presentation.component.Awal

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TombolMulai(
    text: String = "Mulai Sekarang",
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var maxWidth by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    var isCompleted by remember { mutableStateOf(false) }

    val animatedOffset = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current

    // Warna hijau sesuai yang diminta
    val greenColor = Color(0xFF092B1B)

    // Threshold untuk menyelesaikan slide (80% dari lebar)
    val completionThreshold = maxWidth * 0.8f
    val buttonSize = with(density) { 50.dp.toPx() }

    LaunchedEffect(isCompleted) {
        if (isCompleted) {
            // Animasi slide hingga ujung
            animatedOffset.animateTo(
                targetValue = maxWidth - buttonSize,
                animationSpec = tween(300)
            )
            onClick()
            // Tidak ada reset, tetap di posisi akhir
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(Color.White)
            .border(2.dp, greenColor.copy(alpha = 0.3f), RoundedCornerShape(30.dp))
            .onSizeChanged { size ->
                maxWidth = size.width.toFloat()
            }
    ) {
        // Background text
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = greenColor.copy(alpha = if (isDragging) 0.3f else 0.7f)
            )
        }

        // Draggable button
        Box(
            modifier = Modifier
                .size(58.dp)
                .offset {
                    IntOffset(
                        x = if (isCompleted) animatedOffset.value.roundToInt()
                        else offsetX.roundToInt(),
                        y = 0
                    )
                }
                .padding(5.dp)
                .clip(CircleShape)
                .background(greenColor)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            isDragging = true
                        },
                        onDragEnd = {
                            isDragging = false
                            if (offsetX >= completionThreshold) {
                                isCompleted = true
                            } else {
                                // Snap back to start
                                scope.launch {
                                    animatedOffset.snapTo(offsetX)
                                    animatedOffset.animateTo(
                                        targetValue = 0f,
                                        animationSpec = tween(300)
                                    )
                                    offsetX = animatedOffset.value
                                }
                            }
                        }
                    ) { _, dragAmount ->
                        val newOffset = (offsetX + dragAmount.x).coerceIn(
                            0f,
                            maxWidth - buttonSize
                        )
                        offsetX = newOffset
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pesawat_kertas),
                contentDescription = "iconpesawatkertas",
                modifier = Modifier
                    .size(26.dp)
                    .alpha(if (isDragging) 0.9f else 1f)
            )
        }

        // Arrow indicators (optional)
        if (!isDragging && !isCompleted) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { index ->
                    Text(
                        text = "›",
                        color = greenColor.copy(alpha = 0.4f - (index * 0.1f)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (index < 2) Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun TombolMulaiPreview() {
    TombolMulai()
}