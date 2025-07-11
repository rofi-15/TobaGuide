package com.example.tobaguide.presentation.screens.itinerary.ai

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.TravelPlanHeader

// Rest of your code remains the same...

@Composable
fun BudgetSelectionChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF10B981) else Color.White,
        animationSpec = tween(durationMillis = 300),
        label = "background_color"
    )
    val animatedBorderColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF10B981) else Color(0xFFE5E7EB),
        animationSpec = tween(durationMillis = 300),
        label = "border_color"
    )
    val animatedTextColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color(0xFF6B7280),
        animationSpec = tween(durationMillis = 300),
        label = "text_color"
    )

    Box(
        modifier = modifier
            .height(40.dp)
            .background(
                color = animatedColor,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = animatedBorderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = animatedTextColor,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Composable
fun CategorySelectionDialog(
    isVisible: Boolean,
    selectedCategories: List<String>,
    onCategoryToggled: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 600.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Pilih Kategori Aktivitas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937),
                        modifier = Modifier.padding(bottom = 8.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Pilih beberapa kategori yang diminati (max 5)",
                        fontSize = 12.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 20.dp),
                        textAlign = TextAlign.Center
                    )

                    val categories = listOf(
                        listOf("Pemandangan", "Fotografi", "Santai", "Camping"),
                        listOf("Trekking", "Berenang", "Piknik", "Aktivitas Air"),
                        listOf("Perahu", "Memancing", "Hiburan", "Hiking"),
                        listOf("Edukasi", "Banana Boat", "Olahraga", "Fauna"),
                        listOf("Eksplorasi", "Jet Ski", "Berendam", "Air Panas"),
                        listOf("Transportasi", "Goa", "Spotted Air", "Lainnya")
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f, false)
                    ) {
                        items(categories) { rowCategories ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowCategories.forEach { category ->
                                    CategoryChip(
                                        text = category,
                                        isSelected = selectedCategories.contains(category),
                                        onClick = {
                                            if (selectedCategories.contains(category) || selectedCategories.size < 5) {
                                                onCategoryToggled(category)
                                            }
                                        },
                                        modifier = Modifier.weight(1f),
                                        isDisabled = !selectedCategories.contains(category) && selectedCategories.size >= 5
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Selected categories count
                    Text(
                        text = "Dipilih: ${selectedCategories.size}/5",
                        fontSize = 12.sp,
                        color = if (selectedCategories.size >= 5) Color(0xFFEF4444) else Color(0xFF6B7280),
                        modifier = Modifier.padding(bottom = 16.dp),
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Clear all button
                        Button(
                            onClick = {
                                // Clear all selections
                                selectedCategories.forEach { onCategoryToggled(it) }
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFEF4444),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = selectedCategories.isNotEmpty()
                        ) {
                            Text("Hapus Semua", fontSize = 12.sp)
                        }

                        // Done button
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF10B981),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Selesai", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false
) {
    val backgroundColor = when {
        isSelected -> Color(0xFF10B981)
        isDisabled -> Color(0xFFF3F4F6)
        else -> Color(0xFFF9FAFB)
    }

    val borderColor = when {
        isSelected -> Color(0xFF10B981)
        isDisabled -> Color(0xFFD1D5DB)
        else -> Color(0xFFE5E7EB)
    }

    val textColor = when {
        isSelected -> Color.White
        isDisabled -> Color(0xFF9CA3AF)
        else -> Color(0xFF6B7280)
    }

    Box(
        modifier = modifier
            .height(36.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(18.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(enabled = !isDisabled) { onClick() }
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = textColor,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RecommendationCard(
    title: String,
    onAddClick: () -> Unit,
    onBudgetClick: () -> Unit,
    onRemoveClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1F2937)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Add button
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFF10B981), RoundedCornerShape(6.dp))
                            .clickable { onAddClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "+",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Remove button (only show if onRemoveClick is provided)
                    onRemoveClick?.let { removeClick ->
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color(0xFFEF4444), RoundedCornerShape(6.dp))
                                .clickable { removeClick() },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "−",
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Budget button (money icon)
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFF3F4F6), RoundedCornerShape(6.dp))
                            .clickable { onBudgetClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("💰", fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Saran Tempat",
                fontSize = 12.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Destination suggestions
            repeat(3) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .background(Color(0xFFF9FAFB), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "-",
                            fontSize = 14.sp,
                            color = Color(0xFF9CA3AF)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(Color(0xFFF9FAFB), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "00:00 - 00:00",
                            fontSize = 12.sp,
                            color = Color(0xFF9CA3AF)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GenerateItineraryButton(
    onClick: () -> Unit,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color(0xFF10B981), RoundedCornerShape(12.dp))
            .clickable(enabled = !isLoading) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
                Text(
                    text = "Membuat Rencana...",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        } else {
            Text(
                text = "🤖 Buat Rencana dengan AI",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}

@Composable
fun AITravelPlanningScreen(
    onNavigateBack: () -> Unit = {},
    onGenerateItinerary: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var participantCount by remember { mutableIntStateOf(0) }
    var selectedBudget by remember { mutableStateOf("") }
    var selectedCategories by remember { mutableStateOf(listOf<String>()) }
    var showCategoryDialog by remember { mutableStateOf(false) }
    var isGenerating by remember { mutableStateOf(false) }

    // State untuk mengatur jumlah rekomendasi yang ditampilkan
    var visibleRecommendations by remember { mutableIntStateOf(1) }
    val maxRecommendations = 5 // Maksimal 5 rekomendasi

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
            .padding(top = 15.dp, start = 23.dp, end = 23.dp)
    ) {
        TravelPlanHeader(
            title = "Hari 1",
            onBackClick = onNavigateBack
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Travel Date Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Tanggal Perjalanan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1F2937)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Tanggal Mulai",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(Color(0xFFF9FAFB), RoundedCornerShape(8.dp))
                                    .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("🚩", fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (startDate.isEmpty()) "dd/mm/yy" else startDate,
                                        fontSize = 14.sp,
                                        color = if (startDate.isEmpty()) Color(0xFF9CA3AF) else Color(0xFF374151)
                                    )
                                }
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Tanggal Selesai",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(Color(0xFFF9FAFB), RoundedCornerShape(8.dp))
                                    .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("🏁", fontSize = 16.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (endDate.isEmpty()) "dd/mm/yy" else endDate,
                                        fontSize = 14.sp,
                                        color = if (endDate.isEmpty()) Color(0xFF9CA3AF) else Color(0xFF374151)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Travel Details Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Detail Perjalanan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1F2937)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Jumlah orang",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(Color(0xFFF9FAFB), RoundedCornerShape(8.dp))
                                    .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(8.dp)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable {
                                            if (participantCount > 0) participantCount--
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("−", fontSize = 20.sp, color = Color(0xFF6B7280))
                                }

                                Text(
                                    text = participantCount.toString(),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF374151)
                                )

                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable { participantCount++ },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("+", fontSize = 20.sp, color = Color(0xFF6B7280))
                                }
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Kategori Aktivitas",
                                fontSize = 12.sp,
                                color = Color(0xFF6B7280),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(Color(0xFFF9FAFB), RoundedCornerShape(8.dp))
                                    .border(1.dp, Color(0xFFE5E7EB), RoundedCornerShape(8.dp))
                                    .clickable { showCategoryDialog = true },
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = when {
                                            selectedCategories.isEmpty() -> "Pilih Kategori"
                                            selectedCategories.size == 1 -> selectedCategories.first()
                                            else -> "${selectedCategories.size} kategori dipilih"
                                        },
                                        fontSize = 14.sp,
                                        color = if (selectedCategories.isEmpty()) Color(0xFF9CA3AF) else Color(0xFF374151)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        if (selectedCategories.isNotEmpty()) {
                                            Box(
                                                modifier = Modifier
                                                    .size(18.dp)
                                                    .background(Color(0xFF10B981), RoundedCornerShape(9.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = selectedCategories.size.toString(),
                                                    fontSize = 10.sp,
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                        Text(
                                            text = "▼",
                                            fontSize = 12.sp,
                                            color = Color(0xFF6B7280)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Budget Selection
                    Text(
                        text = "Perkiraan Biaya (per orang)",
                        fontSize = 12.sp,
                        color = Color(0xFF6B7280),
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BudgetSelectionChip(
                            text = "<1 Juta",
                            isSelected = selectedBudget == "<1 Juta",
                            onClick = { selectedBudget = "<1 Juta" },
                            modifier = Modifier.weight(1f)
                        )
                        BudgetSelectionChip(
                            text = "1-2 Juta",
                            isSelected = selectedBudget == "1-2 Juta",
                            onClick = { selectedBudget = "1-2 Juta" },
                            modifier = Modifier.weight(1f)
                        )
                        BudgetSelectionChip(
                            text = "2+ Juta",
                            isSelected = selectedBudget == "2+ Juta",
                            onClick = { selectedBudget = "2+ Juta" },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // AI Recommendations Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hasil Rekomendasi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1F2937)
                )

                Text(
                    text = "${visibleRecommendations}/$maxRecommendations rekomendasi",
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280)
                )
            }

            // Dynamic Recommendations
            repeat(visibleRecommendations) { index ->
                RecommendationCard(
                    title = "Rekomendasi ${index + 1}",
                    onAddClick = {
                        // Tambah rekomendasi baru jika belum mencapai maksimal
                        if (visibleRecommendations < maxRecommendations) {
                            visibleRecommendations++
                        }
                    },
                    onBudgetClick = {
                        println("Budget clicked for recommendation ${index + 1}")
                    },
                    onRemoveClick = if (index > 0) {
                        {
                            if (visibleRecommendations > 1) {
                                visibleRecommendations--
                            }
                        }
                    } else null,
                    modifier = Modifier.padding(bottom = if (index == visibleRecommendations - 1) 0.dp else 8.dp)
                )
            }

            if (visibleRecommendations < maxRecommendations) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { visibleRecommendations++ },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0FDF4)),
                    border = BorderStroke(1.dp, Color(0xFF10B981))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "+",
                            fontSize = 18.sp,
                            color = Color(0xFF10B981),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tambah Rekomendasi Baru",
                            fontSize = 14.sp,
                            color = Color(0xFF10B981),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        // Category Selection Dialog
        CategorySelectionDialog(
            isVisible = showCategoryDialog,
            selectedCategories = selectedCategories,
            onCategoryToggled = { category ->
                selectedCategories = if (selectedCategories.contains(category)) {
                    selectedCategories - category
                } else {
                    selectedCategories + category
                }
            },
            onDismiss = { showCategoryDialog = false }
        )

        // Generate Button
        GenerateItineraryButton(
            onClick = {
                isGenerating = true
                onGenerateItinerary()
            },
            isLoading = isGenerating,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AITravelPlanningScreenPreview() {
    AITravelPlanningScreen(
        onNavigateBack = { println("Navigate back") },
        onGenerateItinerary = { println("Generate AI itinerary") }
    )
}