package com.example.tobaguide.presentation.component.Awal.Etika_dan_Budaya

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme

@Composable
fun ContentListComponent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ContentItemComponent(
            number = "1",
            title = "Hormati Adat dan Tradisi Batak",
            description = "Danau Toba adalah wilayah masyarakat Batak yang kaya akan adat istiadat. Hormati upacara adat, ritual, dan tradisi lokal dengan tidak mengganggu atau bersikap tidak sopan. Jika mengunjungi desa adat (seperti Tomok atau Simanindo), mintalah izin sebelum mengambil foto orang atau rumah adat."
        )
        ContentItemComponent(
            number = "2",
            title = "Jaga Kebersihan Lingkungan",
            description = "Danau Toba adalah warisan alam yang harus dilestarikan. Jangan meninggalkan sampah sembarangan, terutama plastik, ke tempat atau area yang tidak tepat. Gunakan tempat sampah yang disediakan atau bawa sampah Anda ke kota atau kota terdekat untuk pembuangan yang tepat."
        )
        ContentItemComponent(
            number = "3",
            title = "Pakai Pakaian Sopan di Tempat Sakral",
            description = "Ketika mengunjungi makam Raja Sidabutar (Tomok) atau tempat-tempat lain, kenakan pakaian yang sopan dan tidak terlalu terbuka."
        )
        ContentItemComponent(
            number = "4",
            title = "Hargai Warga Lokal dengan Bersikap Ramah",
            description = "Berikan 'Horas' (salam khas Batak) saat menyapa warga. Jangan bersikap keras atau berperilaku tidak pantas di pemukiman warga."
        )
        ContentItemComponent(
            number = "5",
            title = "Dukung Ekonomi Lokal",
            description = "Belilah oleh-oleh dari kuliner dari pedagang lokal seperti Ikan Bakar, Arsik, dan lain-lain (atau ulos) untuk mendukung perekonomian masyarakat lokal."
        )
        ContentItemComponent(
            number = "6",
            title = "Patuhi Aturan di Air",
            description = "Ikuti panduan keamanan kapal, jadi panduan keselamatan dan jangan merusak ekosistem danau."
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentListComponentPreview() {
    MaterialTheme {
        ContentListComponent(
            modifier = Modifier.padding(16.dp)
        )
    }
}