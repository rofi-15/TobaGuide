
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tobaguide.presentation.component.Awal.GambarLatar
import com.example.tobaguide.presentation.component.Awal.Text
import com.example.tobaguide.presentation.component.Awal.TombolMulai
import com.example.tobaguide.presentation.component.Gradasi

@Composable
fun MulaiScreen(
    onStartClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GambarLatar()

        Gradasi()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                title = "Keindahan\nDalam Cekungan",
                subtitle = "Rasakan dari ketinggian dahsyat Gunung Toba,\nmenikmati Danau Toba, terbesar se Asia Tenggara"
            )

            Spacer(modifier = Modifier.height(24.dp))

            TombolMulai(
                text = "Mulai Sekarang",
                onClick = onStartClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MulaiScreenPreview() {
    MaterialTheme {
        MulaiScreen()
    }
}