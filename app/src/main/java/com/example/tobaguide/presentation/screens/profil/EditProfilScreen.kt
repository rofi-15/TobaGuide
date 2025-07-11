package com.example.tobaguide.presentation.component.profil.Edit_Profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditProfileScreen(
    onBackClick: () -> Unit = {}, // TAMBAHAN: Parameter untuk navigasi kembali
    onSaveProfile: () -> Unit = {} // TAMBAHAN: Parameter untuk save profile
) {
    var name by remember { mutableStateOf("Roji Fahrofi") }
    var username by remember { mutableStateOf("Roji") }
    var email by remember { mutableStateOf("rofianakotak@yayam.com") }
    var phoneNumber by remember { mutableStateOf("+62882-2238-1678") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        HeaderSection(onBackClick = onBackClick) // PERUBAHAN: Pass callback ke HeaderSection

        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Profile Avatar Section
            ProfileAvatarSection(
                name = name,
                title = "Pejuabang Terbang Morawa",
                onAvatarClick = { /* Handle avatar click */ }
            )

            // Form Section
            FormSection(
                name = name,
                username = username,
                email = email,
                phoneNumber = phoneNumber,
                onNameChange = { name = it },
                onUsernameChange = { username = it },
                onEmailChange = { email = it },
                onPhoneNumberChange = { phoneNumber = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Save Button
            SaveButton(
                onSaveClick = {
                    // Handle save logic
                    println("Saving profile: Name=$name, Username=$username, Email=$email, Phone=$phoneNumber")
                    onSaveProfile() // PERUBAHAN: Call save callback
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(
        onBackClick = {
            println("Back button clicked")
        },
        onSaveProfile = {
            println("Profile saved")
        }
    )
}