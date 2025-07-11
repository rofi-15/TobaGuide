package com.example.tobaguide.presentation.component.itinerary.Tampilan_Awal_Itinerary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.domain.model.ItineraryPlan
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun ItineraryHeader(itineraries: List<ItineraryPlan>) {
    // Logika untuk menghitung statistik berdasarkan daftar itineraries
    val totalCount = itineraries.size

    // Asumsi format tanggal Anda adalah "dd/MM/yy". Sesuaikan jika berbeda.
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yy")
    val today = LocalDate.now()

    val upcomingCount = itineraries.count {
        try {
            // Cek apakah tanggal mulai itinerary ada di masa depan
            LocalDate.parse(it.startDate, dateFormat).isAfter(today)
        } catch (e: DateTimeParseException) {
            false // Abaikan jika format tanggal salah
        }
    }

    val completedCount = itineraries.count {
        try {
            // Cek apakah tanggal selesai itinerary sudah lewat
            LocalDate.parse(it.endDate, dateFormat).isBefore(today)
        } catch (e: DateTimeParseException) {
            false // Abaikan jika format tanggal salah
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Itinerary",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, top = 4.dp),
            textAlign = TextAlign.Start
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Gunakan hasil perhitungan di CounterCard
            CounterCard(label = "Rencana Perjalanan", count = totalCount)
            CounterCard(label = "Mendatang", count = upcomingCount)
            CounterCard(label = "Selesai", count = completedCount)
        }
    }
}

@Composable
fun CounterCard(label: String, count: Int) {
    Surface(
        modifier = Modifier
            .height(80.dp)
            .width(110.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF9E9E9E),
                textAlign = TextAlign.Center
            )
        }
    }
}

// Preview untuk melihat hasil header dengan data dummy
@Preview(showBackground = true)
@Composable
fun ItineraryHeaderPreview() {
    // Kita buat data dummy untuk ditampilkan di preview
    val dummyList = listOf(
        ItineraryPlan(id = "1", title = "Trip 1", startDate = "01/01/25", endDate = "01/01/25"),
        ItineraryPlan(id = "2", title = "Trip 2", startDate = "27/06/25", endDate = "28/06/25")
    )
    ItineraryHeader(itineraries = dummyList)
}