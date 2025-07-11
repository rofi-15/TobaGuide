package com.example.tobaguide.presentation.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.tobaguide.presentation.component.reward.Header
import com.example.tobaguide.presentation.component.reward.Tab


@Preview
@Composable
fun RewardScreen(){
    Column(
        modifier = Modifier
            .background(Color.White)
    ){
        Box(){
            Header()
        }
        Box(){
            Tab()
        }
    }
}