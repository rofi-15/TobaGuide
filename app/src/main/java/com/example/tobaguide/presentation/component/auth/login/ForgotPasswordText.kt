package com.example.tobaguide.presentation.component.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgotPasswordText(
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "Lupa Kata Sandi?",
            color = Color(0xFF2E7D32),
            fontSize = 14.sp,
            modifier = Modifier.clickable { onForgotPasswordClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordTextPreview() {
    MaterialTheme {
        Surface(
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            ForgotPasswordText(
                onForgotPasswordClick = {
                    println("Forgot password clicked")
                }
            )
        }
    }
}