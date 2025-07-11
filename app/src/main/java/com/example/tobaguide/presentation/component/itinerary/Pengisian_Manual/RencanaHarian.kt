package com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.domain.model.DayPlan
import com.example.tobaguide.domain.model.ActivityEntry

@Composable
fun DailyPlanSection(
    dailyPlans: List<DayPlan>,
    onAddPlan: () -> Unit,
    onRemovePlan: (Int) -> Unit,
    onPlanChange: (Int, DayPlan) -> Unit,
    onMoneyClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rencana Harian",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionIcon(Icons.Default.AttachMoney, "Money") { onMoneyClick() }
                ActionIcon(Icons.Default.Add, "Add Plan") { onAddPlan() }
                ActionIcon(Icons.Default.Delete, "Delete") {
                    if (dailyPlans.isNotEmpty()) {
                        onRemovePlan(dailyPlans.size - 1)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tempat yang akan dikunjungi",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Kita gunakan Column biasa karena di dalam ScrollableDailyPlanSection sudah ada scroll
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (dailyPlans.isEmpty()) {
                // Tampilkan 1 item kosong sebagai placeholder
                DailyPlanItem(
                    plan = DayPlan(destination = "Contoh: Pulau Samosir"),
                    onPlanChange = { /* Tidak melakukan apa-apa di placeholder */ }
                )
            } else {
                dailyPlans.forEachIndexed { index, plan ->
                    DailyPlanItem(
                        plan = plan,
                        onPlanChange = { newPlan -> onPlanChange(index, newPlan) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = Modifier.size(24.dp).clickable { onClick() },
        tint = Color.Black
    )
}

@Composable
fun DailyPlanItem(
    plan: DayPlan,
    onPlanChange: (DayPlan) -> Unit,
    modifier: Modifier = Modifier
) {
    // DailyPlanItem sekarang berisi destinasi dan daftar aktivitasnya
    Column(modifier = modifier
        .fillMaxWidth()
        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
        .padding(12.dp)
    ) {
        // Dropdown untuk destinasi
        LocationDropdown(
            value = plan.destination, // <-- MENGGUNAKAN .destination
            onValueChange = { newDestination ->
                onPlanChange(plan.copy(destination = newDestination)) // <-- Mengubah .destination
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Menampilkan daftar aktivitas (untuk sekarang hanya teks)
        plan.activities.forEach { activity ->
            Text(
                text = "- ${activity.description} (${activity.time})", // <-- Menggunakan .description dan .time dari ActivityEntry
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


@Composable
private fun LocationDropdown(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Komponen ini tidak perlu diubah karena hanya menerima String
    Box(
        modifier = modifier
            .height(48.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { /* Handle location selection */ }
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (value.isEmpty()) "Pilih destinasi..." else value,
                fontSize = 14.sp,
                color = if (value.isEmpty()) Color.Gray else Color.Black
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DailyPlanSectionPreview() {
    DailyPlanSection(
        dailyPlans = listOf(
            DayPlan(destination = "Pulau Samosir", activities = listOf(
                ActivityEntry(description = "Kunjungi Desa Tomok"),
                ActivityEntry(description = "Makan siang di tepi danau")
            ))
        ),
        onAddPlan = {},
        onRemovePlan = {},
        onPlanChange = { _, _ -> },
        onMoneyClick = {}
    )
}