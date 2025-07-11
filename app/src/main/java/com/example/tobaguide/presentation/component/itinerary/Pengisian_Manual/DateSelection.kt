package com.example.tobaguide.presentation.component.itinerary.Pengisian_Manual

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelectionSection(
    startDate: String,
    endDate: String,
    participantCount: Int,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit,
    onParticipantCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }

    val startDatePickerState = rememberDatePickerState()
    val endDatePickerState = rememberDatePickerState()

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Tanggal Perjalanan",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Input tanggal mulai
            DateInputField(
                label = "Tanggal Mulai",
                value = startDate,
                placeholder = "dd/mm/yy",
                color = Color(0xFFFF6B35),
                onClick = { showStartDatePicker = true },
                modifier = Modifier.weight(1f)
            )

            // Input tanggal selesai
            DateInputField(
                label = "Tanggal Selesai",
                value = endDate,
                placeholder = "dd/mm/yy",
                color = Color(0xFF22C55E),
                onClick = { showEndDatePicker = true },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Counter jumlah orang
        ParticipantCounter(
            count = participantCount,
            onCountChange = onParticipantCountChange
        )
    }

    // DatePicker untuk tanggal mulai
    if (showStartDatePicker) {
        DatePickerDialog(
            onDateSelected = { selectedDate ->
                onStartDateChange(selectedDate)
                showStartDatePicker = false
            },
            onDismiss = { showStartDatePicker = false },
            datePickerState = startDatePickerState
        )
    }

    // DatePicker untuk tanggal selesai
    if (showEndDatePicker) {
        DatePickerDialog(
            onDateSelected = { selectedDate ->
                onEndDateChange(selectedDate)
                showEndDatePicker = false
            },
            onDismiss = { showEndDatePicker = false },
            datePickerState = endDatePickerState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit,
    datePickerState: DatePickerState
) {
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(selectedDate)
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("Batal")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Composable
private fun DateInputField(
    label: String,
    value: String,
    placeholder: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .clickable { onClick() }
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(color, RoundedCornerShape(2.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (value.isEmpty()) placeholder else value,
                    fontSize = 14.sp,
                    color = if (value.isEmpty()) Color.Gray else Color.Black
                )
            }
        }
    }
}

@Composable
private fun ParticipantCounter(
    count: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Jumlah orang",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CounterButton(
                icon = Icons.Default.Remove,
                onClick = { if (count > 0) onCountChange(count - 1) }
            )


            Text(
                text = count.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )


            CounterButton(
                icon = Icons.Default.Add,
                onClick = { onCountChange(count + 1) }
            )
        }
    }
}

@Composable
private fun CounterButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
fun DateSelectionSectionPreview() {
    DateSelectionSection(
        startDate = "",
        endDate = "",
        participantCount = 0,
        onStartDateChange = {},
        onEndDateChange = {},
        onParticipantCountChange = {}
    )
}