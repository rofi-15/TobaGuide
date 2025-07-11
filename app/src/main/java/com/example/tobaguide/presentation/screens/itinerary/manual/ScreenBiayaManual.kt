package com.example.tobaguide.presentation.screens.itinerary.manual

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BudgetItem(
    val id: String = "",
    val name: String = "",
    val cost: Int = 0
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBiayaScreen(
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var travelerCount by remember { mutableStateOf(1) }
    var budgetItems by remember {
        mutableStateOf(
            listOf(
                BudgetItem("1", "Transportasi", 0),
                BudgetItem("2", "Akomodasi", 0),
                BudgetItem("3", "Makanan", 0)
            )
        )
    }

    // Kalkulasi total biaya
    val totalCost = budgetItems.sumOf { it.cost } * travelerCount

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(horizontal = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Detail Biaya",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }

        // Content dalam LazyColumn
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Date Section
            item {
                Text(
                    text = "Sabtu 10 Mei 2025",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Budget Items
            items(budgetItems.size) { index ->
                val item = budgetItems[index]
                BudgetItemCard(
                    budgetItem = item,
                    onNameChange = { newName ->
                        budgetItems = budgetItems.toMutableList().apply {
                            this[index] = this[index].copy(name = newName)
                        }
                    },
                    onCostChange = { newCost ->
                        budgetItems = budgetItems.toMutableList().apply {
                            this[index] = this[index].copy(cost = newCost)
                        }
                    },
                    onDelete = {
                        budgetItems = budgetItems.toMutableList().apply {
                            removeAt(index)
                        }
                    },
                    showDelete = budgetItems.size > 1
                )
            }

            // Add New Item Button
            item {
                AddBudgetItemCard(
                    onAddClick = {
                        val newId = (budgetItems.size + 1).toString()
                        budgetItems = budgetItems + BudgetItem(newId, "", 0)
                    }
                )
            }

            // Spacing before summary
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Cost Summary
            item {
                SummaryCard(
                    travelerCount = travelerCount,
                    onTravelerCountChange = { newCount ->
                        travelerCount = newCount
                    },
                    totalCost = totalCost
                )
            }

            // Bottom spacing
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun BudgetItemCard(
    budgetItem: BudgetItem,
    onNameChange: (String) -> Unit,
    onCostChange: (Int) -> Unit,
    onDelete: () -> Unit,
    showDelete: Boolean,
    modifier: Modifier = Modifier
) {
    var nameText by remember { mutableStateOf(budgetItem.name) }
    var costText by remember { mutableStateOf(if (budgetItem.cost == 0) "" else budgetItem.cost.toString()) }

    LaunchedEffect(budgetItem.name) {
        nameText = budgetItem.name
    }

    LaunchedEffect(budgetItem.cost) {
        costText = if (budgetItem.cost == 0) "" else budgetItem.cost.toString()
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Orange indicator circle
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = Color(0xFFFF9500),
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Editable name field
                BasicTextField(
                    value = nameText,
                    onValueChange = { newValue ->
                        nameText = newValue
                        onNameChange(newValue)
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) { innerTextField ->
                    Box {
                        if (nameText.isEmpty()) {
                            Text(
                                text = "Nama budget...",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                }
            }

            // Delete button (if more than 1 item)
            if (showDelete) {
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Price field
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rp",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 4.dp)
                )

                BasicTextField(
                    value = costText,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            costText = newValue
                            val newCost = newValue.toIntOrNull() ?: 0
                            onCostChange(newCost)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier.width(80.dp)
                ) { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        if (costText.isEmpty()) {
                            Text(
                                text = "-",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                }
            }
        }
    }
}

@Composable
fun AddBudgetItemCard(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAddClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color(0xFFFF9500),
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Tambah Budget",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFFF9500)
            )
        }
    }
}

@Composable
fun SummaryCard(
    travelerCount: Int,
    onTravelerCountChange: (Int) -> Unit,
    totalCost: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Traveler section with controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Traveler",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Decrease button
                    IconButton(
                        onClick = {
                            if (travelerCount > 1) {
                                onTravelerCountChange(travelerCount - 1)
                            }
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text(
                            text = "-",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF9500)
                        )
                    }

                    Text(
                        text = "$travelerCount Orang",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    // Increase button
                    IconButton(
                        onClick = {
                            onTravelerCountChange(travelerCount + 1)
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text(
                            text = "+",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF9500)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Total section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Text(
                    text = if (totalCost == 0) "Rp -" else "Rp ${String.format("%,d", totalCost)}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailBiayaScreenPreview() {
    MaterialTheme {
        DetailBiayaScreen()
    }
}