package com.example.tobaguide.presentation.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.Awal.Etika_dan_Budaya.ActionButtonComponent
import com.example.tobaguide.presentation.component.Awal.Etika_dan_Budaya.ContentListComponent
import com.example.tobaguide.presentation.component.Awal.Etika_dan_Budaya.HeaderComponent

@Composable
fun EtikaDanBudayaScreen(
    onContinueClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderComponent(
            title = "Etika & Budaya Lokal yang Harus Dijaga Wisatawan di Danau Toba"
        )
        Spacer(modifier = Modifier.height(24.dp))
        ContentListComponent(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        ActionButtonComponent(
            text = "Lanjutkan",
            onClick = onContinueClick,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun EtikaDanBudayaScreenPreview() {
    EtikaDanBudayaScreen()
}
