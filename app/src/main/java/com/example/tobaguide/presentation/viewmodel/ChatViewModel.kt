package com.example.tobaguide.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tobaguide.domain.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    // Pindahkan sampleMessages ke atas sebelum digunakan
    private val sampleMessages = listOf(
        ChatMessage(
            id = "1",
            message = "Halo! Selamat datang di Toba Guide AI.\nApa ada yang bisa saya bantu?",
            isFromUser = false,
            timestamp = "10:27 AM"
        )
    )

    // State untuk menyimpan riwayat percakapan
    private val _messages = MutableStateFlow(sampleMessages)
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    // Fungsi utama yang akan dipanggil oleh UI
    fun sendMessage(messageText: String) {
        // 1. Tambahkan pesan pengguna ke daftar secara langsung
        val userMessage = ChatMessage(
            id = System.currentTimeMillis().toString(),
            message = messageText,
            isFromUser = true,
            timestamp = getCurrentTime()
        )
        _messages.update { currentMessages -> currentMessages + userMessage }

        // 2. Jalankan proses untuk mendapatkan balasan AI di background
        viewModelScope.launch {
            delay(500) // <-- Menggunakan delay(), BUKAN Thread.sleep()
            val aiResponse = generateAIResponse(messageText)
            // 3. Tambahkan balasan AI ke daftar
            _messages.update { currentMessages -> currentMessages + aiResponse }
        }
    }

    // --- Helper Functions ---
    // (Dalam aplikasi nyata, ini bisa dipindah ke kelas lain/repository)

    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val amPm = if (hour < 12) "AM" else "PM"
        val displayHour = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour
        return String.format(Locale.getDefault(), "%d:%02d %s", displayHour, minute, amPm)
    }

    private fun generateAIResponse(userMessage: String): ChatMessage {
        val responses = mapOf(
            "wisata" to ChatMessage(
                id = System.currentTimeMillis().toString(),
                message = "Tentu! Berikut 3 rekomendasi untuk wisata di sekitar Toba:",
                isFromUser = false,
                timestamp = getCurrentTime(),
                isRecommendation = true,
                recommendations = listOf("Bukit Holbung", "Museum Batak TB Silalahi", "Air Terjun Situmurun")
            ),
            "kuliner" to ChatMessage(
                id = System.currentTimeMillis().toString(),
                message = "Berikut rekomendasi kuliner khas Batak:",
                isFromUser = false,
                timestamp = getCurrentTime(),
                isRecommendation = true,
                recommendations = listOf("Arsik Ikan Mas", "Saksang", "Dali Ni Horbo")
            ),
            "akomodasi" to ChatMessage(
                id = System.currentTimeMillis().toString(),
                message = "Berikut rekomendasi tempat menginap:",
                isFromUser = false,
                timestamp = getCurrentTime(),
                isRecommendation = true,
                recommendations = listOf("Toba Village Inn", "Hotel Niagara Parapat", "Samosir Villa Resort")
            ),
            "default" to ChatMessage(
                id = System.currentTimeMillis().toString(),
                message = "Terima kasih atas pertanyaannya! Ada yang ingin Anda ketahui tentang wisata, kuliner, atau akomodasi di sekitar Toba?",
                isFromUser = false,
                timestamp = getCurrentTime()
            )
        )

        val lowerMessage = userMessage.lowercase()
        return when {
            lowerMessage.contains("wisata") || lowerMessage.contains("rekomendasi") || lowerMessage.contains("tempat") -> responses["wisata"]!!
            lowerMessage.contains("kuliner") || lowerMessage.contains("makanan") || lowerMessage.contains("makan") -> responses["kuliner"]!!
            lowerMessage.contains("hotel") || lowerMessage.contains("menginap") || lowerMessage.contains("akomodasi") -> responses["akomodasi"]!!
            else -> responses["default"]!!
        }
    }
}