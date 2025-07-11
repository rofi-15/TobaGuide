package com.example.tobaguide.presentation.component.itinerary.Tampilan_Awal_Itinerary


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R

// Ini adalah komponen dialog utama kita yang bisa digunakan kembali
@Composable
fun ItinerarySelectionDialog(
    onDismissRequest: () -> Unit,
    onAiClick: () -> Unit,
    onManualClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Pilih Jenis Itinerary",
                fontWeight = FontWeight.Bold
            )
        },
        // Kita gunakan CardItinerary sebagai konten utama dialog
        text = {
            CardItinerary(
                onAIClick = {
                    onAiClick()
                    onDismissRequest() // Tutup dialog
                },
                onManualClick = {
                    onManualClick()
                    onDismissRequest() // Tutup dialog
                }
            )
        },
        confirmButton = {}, // Kosongkan karena aksi sudah ada di dalam CardItinerary
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Tutup")
            }
        },
        shape = RoundedCornerShape(24.dp) // Bentuk dialog agar lebih bagus
    )
}

@Composable
fun CardItinerary(
    onAIClick: () -> Unit,
    onManualClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onAIClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ai),
                    contentDescription = "AI Icon",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("AI Itinerary", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Buat Rekomendasi Itinerary AI", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                }
            }
        }
        Button(
            onClick = onManualClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.manual),
                    contentDescription = "Manual Icon",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Manual Itinerary", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Buat Itinerary Manual", fontSize = 12.sp, color = Color.White.copy(alpha = 0.8f))
                }
            }
        }
    }
}