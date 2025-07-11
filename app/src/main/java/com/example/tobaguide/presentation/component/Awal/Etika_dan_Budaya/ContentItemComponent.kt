package com.example.tobaguide.presentation.component.Awal.Etika_dan_Budaya

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentItemComponent(
    number: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        // Number and Title
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$number.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF092B1B),
                modifier = Modifier.padding(end = 8.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                ),
                color = Color(0xFF092B1B),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                lineHeight = 20.sp
            ),
            color = Color(0xFF333333),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(start = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentItemComponentPreview() {
    ContentItemComponent(
        number = "1",
        title = "Hormati Adat dan Tradisi Batak",
        description = "Danau Toba adalah wilayah masyarakat Batak yang kaya akan adat istiadat. Hormati upacara adat, ritual, dan tradisi lokal. Selalu minta izin sebelum mengambil foto atau memasuki area adat. Jika mengunjungi desa adat (seperti Tomok atau Simanindo), mintalah izin sebelum mengambil foto orang atau rumah adat."
    )
}