package com.example.tobaguide.presentation.screens.profil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.profil.Tampilan_Awal_Profil.CardInformasi
import com.example.tobaguide.presentation.component.profil.Tampilan_Awal_Profil.profil

@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit = {},
    onProfileBorderClick: () -> Unit = {},
    onRewardsHistoryClick: () -> Unit = {} // TAMBAH: Parameter untuk navigasi ke rewards history
) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            profil(
                onAvatarClick = onProfileBorderClick
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CardInformasi(
                onProfileClick = {
                    // Handle profile click (bisa untuk navigasi lain)
                },
                onRewardsHistoryClick = onRewardsHistoryClick, // PERBAIKAN: Pass callback untuk rewards history
                onEditProfileClick = onEditProfileClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        onEditProfileClick = {
            println("Navigate to Edit Profile")
        },
        onProfileBorderClick = {
            println("Navigate to Profile Border")
        },
        onRewardsHistoryClick = {
            println("Navigate to Rewards History")
        }
    )
}