package com.example.tobaguide.presentation.screens.itinerary.manual

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tobaguide.domain.model.DayPlan
import com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.DateSelectionSection
import com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.TravelPlanHeader
import com.example.tobaguide.presentation.viewmodel.ItineraryViewModel

@Composable
fun SavePlanButton(
    onClick: () -> Unit,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(
                if (isLoading) Color(0xFF9CA3AF) else Color(0xFF1F2937),
                RoundedCornerShape(12.dp)
            )
            .clickable(enabled = !isLoading) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Text(
                text = "Simpan Rencana",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}


@Composable
fun TimePickerDialog(
    initialStartHour: Int,
    initialStartMinute: Int,
    initialEndHour: Int,
    initialEndMinute: Int,
    onTimeSelected: (startHour: Int, startMinute: Int, endHour: Int, endMinute: Int) -> Unit,
    onDismiss: () -> Unit
) {
    var startHour by remember { mutableStateOf(initialStartHour) }
    var startMinute by remember { mutableStateOf(initialStartMinute) }
    var endHour by remember { mutableStateOf(initialEndHour) }
    var endMinute by remember { mutableStateOf(initialEndMinute) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Pilih Waktu Kunjungan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2937),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Waktu Mulai",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TimePicker(
                            value = startHour,
                            range = 0..23,
                            onValueChange = { startHour = it },
                            label = "Jam"
                        )

                        Text(":", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                        TimePicker(
                            value = startMinute,
                            range = 0..59,
                            onValueChange = { startMinute = it },
                            label = "Menit"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Waktu Selesai",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TimePicker(
                            value = endHour,
                            range = 0..23,
                            onValueChange = { endHour = it },
                            label = "Jam"
                        )
                        Text(":", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        TimePicker(
                            value = endMinute,
                            range = 0..59,
                            onValueChange = { endMinute = it },
                            label = "Menit"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF3F4F6),
                            contentColor = Color(0xFF6B7280)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Batal")
                    }
                    Button(
                        onClick = {
                            onTimeSelected(startHour, startMinute, endHour, endMinute)
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF10B981)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Pilih")
                    }
                }
            }
        }
    }
}

@Composable
fun TimePicker(
    value: Int,
    range: IntRange,
    onValueChange: (Int) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0xFFF3F4F6), RoundedCornerShape(6.dp))
                .clickable {
                    val newValue = if (value < range.last) value + 1 else range.first
                    onValueChange(newValue)
                },
            contentAlignment = Alignment.Center
        ) {
            Text("▲", fontSize = 12.sp, color = Color(0xFF6B7280))
        }
        Box(
            modifier = Modifier
                .size(60.dp, 40.dp)
                .background(Color(0xFF10B981), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = String.format("%02d", value),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0xFFF3F4F6), RoundedCornerShape(6.dp))
                .clickable {
                    val newValue = if (value > range.first) value - 1 else range.last
                    onValueChange(newValue)
                },
            contentAlignment = Alignment.Center
        ) {
            Text("▼", fontSize = 12.sp, color = Color(0xFF6B7280))
        }
    }
}

@Composable
fun ScrollableDailyPlanSection(
    dayPlans: List<DayPlan>,
    onAddPlan: () -> Unit,
    onRemovePlan: (Int) -> Unit,
    onPlanChange: (Int, DayPlan) -> Unit,
    onMoneyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rencana Harian",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .shadow(2.dp, RoundedCornerShape(6.dp))
                        .background(Color(0xFFF3F4F6), RoundedCornerShape(6.dp))
                        .clickable { onMoneyClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("💰", fontSize = 16.sp)
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .shadow(2.dp, RoundedCornerShape(6.dp))
                        .background(Color(0xFF10B981), RoundedCornerShape(6.dp))
                        .clickable { onAddPlan() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("+", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .shadow(2.dp, RoundedCornerShape(6.dp))
                        .background(Color(0xFFEF4444), RoundedCornerShape(6.dp))
                        .clickable {
                            if (dayPlans.isNotEmpty()) {
                                onRemovePlan(dayPlans.size - 1)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text("🗑", fontSize = 14.sp)
                }
            }
        }

        Text(
            text = "Tempat yang akan dikunjungi",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                dayPlans.forEachIndexed { index, plan ->
                    DailyPlanItem(
                        plan = plan,
                        onPlanChange = { newPlan -> onPlanChange(index, newPlan) }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun DailyPlanItem(
    plan: DayPlan,
    onPlanChange: (DayPlan) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedDestination by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var isSearchMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }

    // Time states
    var startHour by remember { mutableStateOf(9) }
    var startMinute by remember { mutableStateOf(0) }
    var endHour by remember { mutableStateOf(12) }
    var endMinute by remember { mutableStateOf(0) }

    val allDestinations = listOf(
        "🏞️ Danau Toba",
        "🌋 Gunung Sibayak",
        "🏔️ Bukit Lawang",
        "🌊 Air Terjun Sipiso-piso",
        "🌿 Taman Hutan Raya",
        "🦋 Taman Kupu-kupu",
        "🌸 Kebun Raya Medan",

        "🏰 Istana Maimun",
        "🕌 Masjid Raya Al-Mashun",
        "🏛️ Tjong A Fie Mansion",
        "⛪ Gereja Graha Maria Annai Velangkanni",
        "🏛️ Museum Negeri Sumatera Utara",
        "🏯 Rumah Bolon",
        "🏮 Vihara Gunung Timur",

        "🛒 Pasar Petisah",
        "🍜 Mie Aceh Titi Bobrok",
        "🥘 Restoran Garuda",
        "🛍️ Sun Plaza",
        "🏬 Centre Point Mall",
        "🍰 Bika Ambon Zulaikha",
        "☕ Kopi Soe",

        "🚶 Merdeka Walk",
        "🎢 Wonderland Theme Park",
        "🎳 Top Bowl",
        "🎬 XXI Cinema",
        "🏊 Kolam Renang Tirtanadi",
        "⛳ Medan Golf Club",

        "🏘️ Kampung Keling",
        "🇮🇳 Little India Medan",
        "🏮 Kampung Cina",
        "🌅 Jembatan Ampera"
    )

    val filteredDestinations = if (searchQuery.isEmpty()) {
        allDestinations
    } else {
        allDestinations.filter {
            it.lowercase().contains(searchQuery.lowercase())
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(Color(0xFFF9FAFB), RoundedCornerShape(10.dp))
                    .border(
                        width = if (isDropdownExpanded || isSearchMode) 2.dp else 1.dp,
                        color = if (isDropdownExpanded || isSearchMode) Color(0xFF10B981) else Color(0xFFE5E7EB),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        if (!isSearchMode) {
                            isDropdownExpanded = !isDropdownExpanded
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                if (isSearchMode) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🔍", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        BasicTextField(
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                                isDropdownExpanded = true
                            },
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Black
                            ),
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                if (searchQuery.isEmpty()) {
                                    Text(
                                        "Cari destinasi wisata...",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                                innerTextField()
                            }
                        )
                        Text(
                            text = "✕",
                            fontSize = 16.sp,
                            color = Color(0xFF6B7280),
                            modifier = Modifier.clickable {
                                isSearchMode = false
                                isDropdownExpanded = false
                                searchQuery = ""
                            }
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (selectedDestination.isEmpty()) "📍 Pilih destinasi" else selectedDestination,
                            fontSize = 14.sp,
                            color = if (selectedDestination.isEmpty()) Color.Gray else Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = if (isDropdownExpanded) "▲" else "▼",
                            fontSize = 12.sp,
                            color = Color(0xFF6B7280)
                        )
                    }
                }
            }

            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = {
                    isDropdownExpanded = false
                    if (isSearchMode && selectedDestination.isEmpty()) {
                        isSearchMode = false
                        searchQuery = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(10.dp))
            ) {
                if (!isSearchMode) {
                    // Opsi untuk search
                    DropdownMenuItem(
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("🔍", fontSize = 16.sp)
                                Text(
                                    "Cari Destinasi",
                                    fontSize = 14.sp,
                                    color = Color(0xFF10B981),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        },
                        onClick = {
                            isSearchMode = true
                            searchQuery = ""
                        },
                        modifier = Modifier
                            .background(Color(0xFFF0FDF4))
                            .padding(vertical = 4.dp)
                    )

                    Divider(color = Color(0xFFE5E7EB), thickness = 1.dp)
                }

                if (filteredDestinations.isEmpty() && searchQuery.isNotEmpty()) {
                    DropdownMenuItem(
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("❌", fontSize = 16.sp)
                                Text(
                                    "Tidak ada hasil untuk \"$searchQuery\"",
                                    fontSize = 14.sp,
                                    color = Color(0xFF6B7280),
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        },
                        onClick = { },
                        enabled = false
                    )
                } else {
                    if (isSearchMode && searchQuery.isNotEmpty()) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "📋 Ditemukan ${filteredDestinations.size} destinasi",
                                    fontSize = 12.sp,
                                    color = Color(0xFF6B7280),
                                    fontWeight = FontWeight.Medium
                                )
                            },
                            onClick = { },
                            enabled = false,
                            modifier = Modifier.background(Color(0xFFF9FAFB))
                        )

                        Divider(color = Color(0xFFE5E7EB), thickness = 1.dp)
                    }
                    filteredDestinations.forEach { destination ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    destination,
                                    fontSize = 14.sp,
                                    color = Color(0xFF374151)
                                )
                            },
                            onClick = {
                                selectedDestination = destination
                                isDropdownExpanded = false
                                isSearchMode = false
                                searchQuery = ""
                            },
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(Color(0xFFF9FAFB), RoundedCornerShape(10.dp))
                .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(10.dp))
                .clickable { showTimePicker = true },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("🕐", fontSize = 16.sp)
                Text(
                    text = "${String.format("%02d", startHour)}:${String.format("%02d", startMinute)} - ${String.format("%02d", endHour)}:${String.format("%02d", endMinute)}",
                    fontSize = 14.sp,
                    color = Color(0xFF374151),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    // Time Picker Dialog
    if (showTimePicker) {
        TimePickerDialog(
            initialStartHour = startHour,
            initialStartMinute = startMinute,
            initialEndHour = endHour,
            initialEndMinute = endMinute,
            onTimeSelected = { sH, sM, eH, eM ->
                startHour = sH
                startMinute = sM
                endHour = eH
                endMinute = eM
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}

@Composable
fun TravelPlanningScreen(
    onNavigateBack: () -> Unit,
    onSavePlan: () -> Unit,
    onNavigateToDetailBiaya: () -> Unit,
    viewModel: ItineraryViewModel = hiltViewModel()
) {
    val state by viewModel.manualCreationState.collectAsState()

    LaunchedEffect(state.saveSuccess) {
        if (state.saveSuccess) {
            onSavePlan()
            viewModel.clearSaveSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
            .padding(top = 15.dp, start = 23.dp, end = 23.dp)
    ) {
        TravelPlanHeader(
            title = "Perjalanan Anda",
            onBackClick = onNavigateBack
        )
        Spacer(Modifier.height(16.dp))


        DateSelectionSection(
            startDate = state.startDate,
            endDate = state.endDate,
            participantCount = state.participantCount,
            onStartDateChange = viewModel::onStartDateChange,
            onEndDateChange = viewModel::onEndDateChange,
            onParticipantCountChange = viewModel::onParticipantCountChange
        )
        Spacer(Modifier.height(16.dp))

        // ScrollableDailyPlanSection sekarang juga dikontrol oleh ViewModel
        ScrollableDailyPlanSection(
            dayPlans = state.dailyPlans,
            onAddPlan = viewModel::onAddDailyPlan,
            onRemovePlan = { viewModel.onRemoveDailyPlan() }, // Disesuaikan
            onPlanChange = { dayIndex, newPlan ->
                viewModel.onDailyPlanDestinationChange(dayIndex, newPlan.destination)
            },
            onMoneyClick = onNavigateToDetailBiaya,
            modifier = Modifier.weight(1f)
        )

        SavePlanButton(
            onClick = viewModel::saveCurrentManualPlan,
            isLoading = state.isSaving,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TravelPlanningScreenPreview() {
    TravelPlanningScreen(
        onNavigateBack = { println("Navigate back") },
        onSavePlan = { println("Save plan") },
        onNavigateToDetailBiaya = { println("Navigate to detail biaya") }
    )
}