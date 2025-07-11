package com.example.tobaguide.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.notifikasi.NotificationHeader
import com.example.tobaguide.presentation.component.notifikasi.NotificationItem
import com.example.tobaguide.presentation.component.notifikasi.NotificationSection

data class NotificationData(
    val id: String,
    val title: String,
    val message: String,
    val time: String,
    val icon: Int,
    val type: NotificationType = NotificationType.NORMAL
)

enum class NotificationType {
    NORMAL, TRAVEL_SECTION, TOBA_GUIDE
}

@Composable
fun NotificationScreen(
    onBackClick: () -> Unit = {},
    onRatingNavigate: () -> Unit = {}, // Tambahan parameter untuk navigasi ke rating
    modifier: Modifier = Modifier
) {
    val notifications = listOf(
        NotificationData(
            id = "1",
            title = "Rofi anak baik",
            message = "Pemandangan nya bagus, penyusunan sistem perjalanan juga baik",
            time = "Minggu, 7 Mei 2025",
            icon = com.example.tobaguide.R.drawable.profil1,
            type = NotificationType.NORMAL
        ),
        NotificationData(
            id = "2",
            title = "Paus",
            message = "Aplikasinya bagus sekali, destinasi yang direkomendasikan juga sesuai",
            time = "Senin, 28 April 2025",
            icon = com.example.tobaguide.R.drawable.profil1,
            type = NotificationType.NORMAL
        ),
        NotificationData(
            id = "3",
            title = "Pembaruan Aplikasi",
            message = "Mohon maaf untuk pengguna aplikasi kami. Silahkan perbarui ke versi terbaru ada pembaruan",
            time = "Sabtu, 14 Maret 2025",
            icon = com.example.tobaguide.R.drawable.profil1,
            type = NotificationType.NORMAL
        )
    )

    val travelNotifications = listOf(
        NotificationData(
            id = "4",
            title = "Toba Guide",
            message = "Selamat!!! Anda sudah menyelesaikan perjalanan wisata bersama kami. Semoga memberikan kesan yang tak bisa untuk anda\n\nSilahkan klik notifikasi ini untuk memberikan ulasan Anda, agar anda bisa mendapatkan point untuk bisa mencapai misi",
            time = "2 minggu yang lalu",
            icon = com.example.tobaguide.R.drawable.profil1,
            type = NotificationType.TOBA_GUIDE
        ),
        NotificationData(
            id = "5",
            title = "Toba Guide",
            message = "Selamat!!! Anda sudah menyelesaikan perjalanan wisata bersama kami. Semoga memberikan kesan yang tak bisa untuk anda\n\nSilahkan klik notifikasi ini untuk memberikan ulasan Anda, agar anda bisa mendapatkan point untuk bisa mencapai misi",
            time = "2 minggu yang lalu",
            icon = com.example.tobaguide.R.drawable.profil1,
            type = NotificationType.TOBA_GUIDE
        )
    )

    Box(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column {
            NotificationHeader(
                onBackClick = onBackClick
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(notifications) { notification ->
                    NotificationItem(
                        notification = notification,
                        onClick = {
                            // Handle click untuk notifikasi normal
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                }

                item {
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    NotificationSection(title = "Notifikasi Perjalanan")
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                }

                items(travelNotifications) { notification ->
                    NotificationItem(
                        notification = notification,
                        onClick = {
                            // Handle click untuk notifikasi perjalanan
                            if (notification.type == NotificationType.TOBA_GUIDE) {
                                onRatingNavigate() // Navigate ke rating screen
                            }
                        }
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                }

                item {
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(
        onBackClick = {
            println("Preview: Back clicked")
        },
        onRatingNavigate = {
            println("Preview: Navigate to rating")
        }
    )
}