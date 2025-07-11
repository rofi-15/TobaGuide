package com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.Budget_Manual

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import java.text.NumberFormat
import java.util.*

@Composable
fun EditableCostSummary(
    travelerCount: Int = 1,
    onTravelerCountChange: (Int) -> Unit = {},
    activityCosts: List<Int> = emptyList(),
    onActivityCostChange: (Int, Int) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    var travelerCountText by remember { mutableStateOf(travelerCount.toString()) }

    // Calculate total cost
    val totalCost = activityCosts.sum() * travelerCount

    LaunchedEffect(travelerCount) {
        travelerCountText = travelerCount.toString()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        // Traveler Count Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Jumlah Traveler",
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
                            val newCount = travelerCount - 1
                            onTravelerCountChange(newCount)
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

                // Traveler count input
                BasicTextField(
                    value = travelerCountText,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } && newValue.isNotEmpty()) {
                            travelerCountText = newValue
                            val count = newValue.toIntOrNull()
                            if (count != null && count > 0) {
                                onTravelerCountChange(count)
                            }
                        } else if (newValue.isEmpty()) {
                            travelerCountText = ""
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .width(60.dp)
                        .background(
                            color = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                )

                // Increase button
                IconButton(
                    onClick = {
                        val newCount = travelerCount + 1
                        onTravelerCountChange(newCount)
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

                Text(
                    text = "Orang",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Activity Costs Section
        Text(
            text = "Biaya per Aktivitas",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        activityCosts.forEachIndexed { index, cost ->
            ActivityCostItem(
                activityNumber = index + 1,
                cost = cost,
                onCostChange = { newCost ->
                    onActivityCostChange(index, newCost)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total Cost Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF8F9FA),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Biaya",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = formatCurrency(totalCost),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF9500)
            )
        }
    }
}

@Composable
private fun ActivityCostItem(
    activityNumber: Int,
    cost: Int,
    onCostChange: (Int) -> Unit
) {
    var costText by remember { mutableStateOf(if (cost == 0) "" else cost.toString()) }

    LaunchedEffect(cost) {
        costText = if (cost == 0) "" else cost.toString()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Aktivitas $activityNumber",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rp",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
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
                modifier = Modifier
                    .width(100.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(vertical = 8.dp, horizontal = 12.dp)
            ) { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterEnd
                ) {
                    if (costText.isEmpty()) {
                        Text(
                            text = "0",
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

private fun formatCurrency(amount: Int): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    return formatter.format(amount).replace("IDR", "Rp")
}

@Preview(showBackground = true)
@Composable
fun EditableCostSummaryPreview() {
    var travelerCount by remember { mutableStateOf(2) }
    var activityCosts by remember { mutableStateOf(listOf(50000, 75000, 100000)) }

    EditableCostSummary(
        travelerCount = travelerCount,
        onTravelerCountChange = { newCount ->
            travelerCount = newCount
        },
        activityCosts = activityCosts,
        onActivityCostChange = { index, newCost ->
            activityCosts = activityCosts.toMutableList().apply {
                this[index] = newCost
            }
        }
    )
}