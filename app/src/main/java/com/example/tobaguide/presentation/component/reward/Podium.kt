package com.example.tobaguide.presentation.component.reward

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.domain.model.DataClass

@Composable
fun Podium(
    first: DataClass,
    second: DataClass,
    third: DataClass
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {

        PodiumItem(
            user = second,
            height = 120.dp,
            isFirst = false
        )


        PodiumItem(
            user = first,
            height = 160.dp,
            isFirst = true
        )


        PodiumItem(
            user = third,
            height = 100.dp,
            isFirst = false
        )
    }
}

@Composable
fun PodiumItem(
    user: DataClass,
    height: Dp,
    isFirst: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(user.color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = user.avatar,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        Box(
            modifier = Modifier
                .width(40.dp)
                .height(height)
                .background(
                    color = user.color,
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = user.rank.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = user.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Text(
            text = user.points,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PodiumPreview() {
    val sampleUsers = listOf(
        DataClass("Annisa R.", "8,216", 1, 58, "AR", Color(0xFFFF9800)),
        DataClass("Diva Azalia", "8,201", 2, 58, "DA", Color(0xFF2196F3)),
        DataClass("Sancio V.", "7,897", 3, 50, "SVC", Color(0xFF00BCD4))
    )

    Podium(
        first = sampleUsers[0],
        second = sampleUsers[1],
        third = sampleUsers[2]
    )
}