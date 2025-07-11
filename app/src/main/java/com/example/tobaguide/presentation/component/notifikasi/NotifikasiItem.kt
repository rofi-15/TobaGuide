package com.example.tobaguide.presentation.component.notifikasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.presentation.screens.home.NotificationData
import com.example.tobaguide.presentation.screens.home.NotificationType

@Composable
fun NotificationItem(
    notification: NotificationData,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (notification.type) {
        NotificationType.TOBA_GUIDE -> Color(0xFFFFF3E0) // Light orange background
        NotificationType.TRAVEL_SECTION -> Color(0xFFF0F8FF) // Light blue background
        else -> Color.White
    }

    val borderColor = when (notification.type) {
        NotificationType.TOBA_GUIDE -> Color(0xFFFF9800) // Orange border
        NotificationType.TRAVEL_SECTION -> Color(0xFF2196F3) // Blue border
        else -> Color(0xFFE0E0E0) // Gray border
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = notification.icon),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                Text(
                    text = notification.message,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp,
                    maxLines = if (notification.type == NotificationType.TOBA_GUIDE) Int.MAX_VALUE else 2,
                    overflow = if (notification.type == NotificationType.TOBA_GUIDE) TextOverflow.Visible else TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.padding(vertical = 4.dp))

                Text(
                    text = notification.time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // More options icon
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationItemPreview() {
    NotificationItem(
        notification = NotificationData(
            id = "1",
            title = "Rofi anak baik",
            message = "Pemandangan nya bagus, penyusunan sistem perjalanan juga baik",
            time = "Minggu, 7 Mei 2025",
            icon = com.example.tobaguide.R.drawable.profil1
        )
    )
}

@Preview(showBackground = true)
@Composable
fun NotificationItemTobaGuidePreview() {
    NotificationItem(
        notification = NotificationData(
            id = "1",
            title = "Toba Guide",
            message = "Selamat!!! Anda sudah menyelesaikan perjalanan wisata bersama kami. Semoga memberikan kesan yang tak bisa untuk anda\n\nSilahkan klik notifikasi ini untuk memberikan ulasan Anda, agar anda bisa mendapatkan point untuk bisa mencapai misi",
            time = "2 minggu yang lalu",
            icon = com.example.tobaguide.R.drawable.profil1,
            type = NotificationType.TOBA_GUIDE
        )
    )
}