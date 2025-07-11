package com.example.tobaguide.presentation.component.auth.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginInputFields(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = "Email"
        )

        CustomTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "Kata Sandi",
            isPassword = true
        )

        ForgotPasswordText(
            onForgotPasswordClick = onForgotPasswordClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginInputFieldsPreview() {
    MaterialTheme {
        Surface(
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            LoginInputFields(
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onForgotPasswordClick = {
                    println("Forgot password clicked")
                }
            )
        }
    }
}