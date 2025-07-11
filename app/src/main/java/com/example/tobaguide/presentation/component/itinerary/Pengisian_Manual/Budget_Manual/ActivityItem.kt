package com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual.Budget_Manual

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun EditableActivityItem(
    time: String = "00:00 - 00:00",
    activity: String = "Explore Wisata Alam",
    cost: Int = 0,
    onCostChange: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var costText by remember { mutableStateOf(if (cost == 0) "" else cost.toString()) }

    LaunchedEffect(cost) {
        costText = if (cost == 0) "" else cost.toString()
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
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
                    .size(16.dp)
                    .background(
                        color = Color(0xFFFF9500),
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = time,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = activity,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Editable price field
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
                    modifier = Modifier
                        .width(80.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE0E0E0),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(vertical = 6.dp, horizontal = 8.dp)
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

// Simple CostSummary for displaying totals
@Composable
fun SimpleCostSummary(
    travelerCount: Int = 1,
    onTravelerCountChange: (Int) -> Unit = {},
    totalCost: Int = 0,
    modifier: Modifier = Modifier
) {
    var travelerCountText by remember { mutableStateOf(travelerCount.toString()) }

    LaunchedEffect(travelerCount) {
        travelerCountText = travelerCount.toString()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
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

                // Traveler count display
                Text(
                    text = "$travelerCount Orang",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp)
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
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total Cost Section
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
                text = formatCurrency(totalCost),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

private fun formatCurrency(amount: Int): String {
    if (amount == 0) return "Rp -"
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    return formatter.format(amount).replace("IDR", "Rp")
}

@Preview(showBackground = true)
@Composable
fun EditableActivityItemPreview() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        EditableActivityItem(
            time = "08:00 - 10:00",
            activity = "Explore Wisata Alam",
            cost = 50000,
            onCostChange = { }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SimpleCostSummary(
            travelerCount = 2,
            totalCost = 150000
        )
    }
}