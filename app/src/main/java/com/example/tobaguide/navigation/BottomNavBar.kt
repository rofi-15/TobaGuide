package com.example.tobaguide.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tobaguide.R
import com.example.tobaguide.ui.theme.TobaGuideTheme

@Composable
fun BottomNavBar(navController: NavController) {
    val currentRoute by navController.currentBackStackEntryAsState()
    val activeRoute = currentRoute?.destination?.route

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            color = Color(0xFF1B4332),
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavIconButton(
                    iconRes = R.drawable.ic_home,
                    contentDescription = "Home",
                    isSelected = activeRoute == "home",
                    onClick = { navController.navigate("home") }
                )
                NavIconButton(
                    iconRes = R.drawable.ic_itinerary,
                    contentDescription = "Itinerary",
                    isSelected = activeRoute == "itinerary",
                    onClick = { navController.navigate("itinerary") }
                )
                NavIconButton(
                    iconRes = R.drawable.ic_reward,
                    contentDescription = "Reward",
                    isSelected = activeRoute == "reward",
                    onClick = { navController.navigate("reward") }
                )
                NavIconButton(
                    iconRes = R.drawable.ic_profil,
                    contentDescription = "Profile",
                    isSelected = activeRoute == "profile",
                    onClick = { navController.navigate("profile") }
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsBottomHeight(WindowInsets.navigationBars)
                .background(Color(0xFF1B4332))
        )
    }
}
@Composable
fun NavIconButton(
    iconRes: Int,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // Menghilangkan ripple effect
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = contentDescription,
                    tint = Color(0xFF1B4332),
                    modifier = Modifier.size(28.dp)
                )
            }
        } else {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                tint = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavPreview() {
    TobaGuideTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)),
            verticalArrangement = Arrangement.Bottom
        ) {
            BottomNavBar(navController = rememberNavController())
        }
    }
}