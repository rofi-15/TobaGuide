package com.example.tobaguide.presentation.component.profil.Edit_Profil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormSection(
    name: String,
    username: String,
    email: String,
    phoneNumber: String,
    onNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Informasi Pribadi",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                FormField(
                    label = "Nama",
                    value = name,
                    onValueChange = onNameChange
                )

                Divider(
                    color = Color(0xFFF0F0F0),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                FormField(
                    label = "Username",
                    value = username,
                    onValueChange = onUsernameChange
                )

                Divider(
                    color = Color(0xFFF0F0F0),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                FormField(
                    label = "Email",
                    value = email,
                    onValueChange = onEmailChange
                )

                Divider(
                    color = Color(0xFFF0F0F0),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                FormField(
                    label = "Nomor Telepon",
                    value = phoneNumber,
                    onValueChange = onPhoneNumberChange
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormSectionPreview() {
    FormSection(
        name = "Roji Fahrofi",
        username = "Roji",
        email = "rofianakotak@yayam.com",
        phoneNumber = "+62882-2238-1678",
        onNameChange = { /* Preview action */ },
        onUsernameChange = { /* Preview action */ },
        onEmailChange = { /* Preview action */ },
        onPhoneNumberChange = { /* Preview action */ }
    )
}