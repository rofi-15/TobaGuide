package com.example.tobaguide.presentation.screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.auth.login.LoginHeader
import com.example.tobaguide.presentation.component.auth.login.LoginInputFields
import com.example.tobaguide.presentation.component.auth.login.LoginButton
import com.example.tobaguide.presentation.component.auth.login.RegisterLink

@Composable
fun LoginScreen(
    onLoginClick: (email: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val horizontalPadding = when {
        screenWidth < 360.dp -> 20.dp
        screenWidth < 400.dp -> 24.dp
        else -> 32.dp
    }

    val verticalPadding = when {
        screenHeight < 600.dp -> 16.dp
        screenHeight < 700.dp -> 24.dp
        else -> 32.dp
    }

    val componentSpacing = when {
        screenHeight < 600.dp -> 16.dp
        screenHeight < 700.dp -> 20.dp
        else -> 24.dp
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(componentSpacing)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        LoginHeader()
        Spacer(modifier = Modifier.height(32.dp))

        LoginInputFields(
            email = email,
            password = password,
            onEmailChange = { email = it },
            onPasswordChange = { password = it },
            onForgotPasswordClick = onForgotPasswordClick
        )
        Spacer(modifier = Modifier.height(8.dp))
        LoginButton(
            onClick = { onLoginClick(email, password) }
        )
        Spacer(modifier = Modifier.weight(1f))

        RegisterLink(
            onRegisterClick = onRegisterClick
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun LoginScreenCentered(
    onLoginClick: (email: String, password: String) -> Unit = { _, _ -> },
    onForgotPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val horizontalPadding = when {
        screenWidth < 360.dp -> 20.dp
        screenWidth < 400.dp -> 24.dp
        else -> 32.dp
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = horizontalPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Header Section
            LoginHeader()

            // Input Fields Section
            LoginInputFields(
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onForgotPasswordClick = onForgotPasswordClick
            )

            // Login Button Section
            LoginButton(
                onClick = { onLoginClick(email, password) }
            )

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Register Link Section
            RegisterLink(
                onRegisterClick = onRegisterClick
            )
        }
    }
}

// Preview untuk berbagai ukuran layar
@Preview(showBackground = true, widthDp = 360, heightDp = 640, name = "Small Screen")
@Composable
fun LoginScreenSmallPreview() {
    MaterialTheme {
        LoginScreen(
            onLoginClick = { email, password ->
                println("Login clicked: $email, $password")
            },
            onForgotPasswordClick = {
                println("Forgot password clicked")
            },
            onRegisterClick = {
                println("Register clicked")
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800, name = "Medium Screen")
@Composable
fun LoginScreenMediumPreview() {
    MaterialTheme {
        LoginScreen(
            onLoginClick = { email, password ->
                println("Login clicked: $email, $password")
            },
            onForgotPasswordClick = {
                println("Forgot password clicked")
            },
            onRegisterClick = {
                println("Register clicked")
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 480, heightDp = 900, name = "Large Screen")
@Composable
fun LoginScreenLargePreview() {
    MaterialTheme {
        LoginScreenCentered(
            onLoginClick = { email, password ->
                println("Login clicked: $email, $password")
            },
            onForgotPasswordClick = {
                println("Forgot password clicked")
            },
            onRegisterClick = {
                println("Register clicked")
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640, name = "Centered Layout")
@Composable
fun LoginScreenCenteredPreview() {
    MaterialTheme {
        LoginScreenCentered()
    }
}