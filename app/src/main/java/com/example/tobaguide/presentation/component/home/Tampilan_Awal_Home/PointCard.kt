package com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tobaguide.R

@Preview
@Composable
fun PointsCard() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pointku",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(5.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ){
            Row(
                modifier = Modifier.padding(start = 10.dp, top = 20.dp, end = 15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = "coin",
                    modifier = Modifier.size(61.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Column {
                    Row {
                        Text(
                            text = "Gold",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "3000 Point",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Green
                        )
                    }
                    Text(
                        text = "Tambah 3000 point lagi untuk naik ke Platinum",
                        modifier = Modifier.padding(top = 9.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 9.sp
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.LightGray)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(150.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF1B6B41))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PointsCardPreview() {
    PointsCard()
}