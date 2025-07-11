package com.example.tobaguide.presentation.component.home.Tampilan_Awal_Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import com.example.tobaguide.R
import com.example.tobaguide.domain.model.ChatMessage
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt
import androidx.hilt.navigation.compose.hiltViewModel // <-- PASTIKAN IMPORT INI ADA
import com.example.tobaguide.presentation.viewmodel.ChatViewModel // <-- IMPORT VIEWMODEL BARU

@Composable
fun DraggableChatbotContainer(
    // 1. Inject ViewModel di sini
    viewModel: ChatViewModel = hiltViewModel(),
    onAboutClick: () -> Unit = {}
) {
    // State untuk navigasi UI tetap di sini, karena ini hanya urusan tampilan
    var currentScreen by remember { mutableStateOf(ChatScreen.None) }
    // 2. State untuk pesan sekarang diambil dari ViewModel
    val chatMessages by viewModel.messages.collectAsState()

    when (currentScreen) {
        ChatScreen.Chat -> {
            TobaGuideAIChat(
                messages = chatMessages, // <- Gunakan state dari ViewModel
                onSendMessage = { message ->
                    // 3. Logika pengiriman pesan sekarang hanya memanggil ViewModel
                    viewModel.sendMessage(message)
                },
                onBackClick = {
                    currentScreen = ChatScreen.Menu
                }
            )
        }

        ChatScreen.Help -> {
            TobaGuideHelpScreen(
                onBackClick = {
                    currentScreen = ChatScreen.Menu
                }
            )
        }

        ChatScreen.Menu -> {
            ChatbotMenuPopup(
                onChatClick = { currentScreen = ChatScreen.Chat },
                onHelpClick = { currentScreen = ChatScreen.Help },
                onAboutClick = {
                    currentScreen = ChatScreen.None
                    onAboutClick()
                },
                onDismiss = { currentScreen = ChatScreen.None }
            )
        }

        ChatScreen.None -> {
            DraggableChatbot(
                onChatbotClick = { currentScreen = ChatScreen.Menu }
            )
        }
    }
}

// Enum untuk mengatur state navigasi
enum class ChatScreen {
    None,    // Hanya tampilkan floating icon
    Menu,    // Tampilkan popup menu
    Chat,    // Tampilkan chat screen
    Help     // Tampilkan help screen
}

@Composable
fun DraggableChatbot(
    onChatbotClick: () -> Unit = {}
) {
    // Get screen dimensions
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidth = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }
    val botSize = with(density) { 60.dp.toPx() }

    // Position state for dragging
    var offsetX by remember { mutableFloatStateOf(screenWidth - botSize - 32f) } // Start at right side
    var offsetY by remember { mutableFloatStateOf(screenHeight / 2f) } // Start at middle height

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(10f) // Ensure it's on top of other content
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        offsetX.roundToInt(),
                        offsetY.roundToInt()
                    )
                }
                .size(60.dp)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        val newOffsetX = (offsetX + dragAmount.x).coerceIn(
                            0f,
                            screenWidth - botSize
                        )
                        val newOffsetY = (offsetY + dragAmount.y).coerceIn(
                            0f,
                            screenHeight - botSize - 200f // Account for bottom navigation/padding
                        )

                        offsetX = newOffsetX
                        offsetY = newOffsetY
                    }
                }
        ) {
            FloatingActionButton(
                onClick = onChatbotClick,
                containerColor = Color.White,
                contentColor = Color.White,
                modifier = Modifier
                    .size(70.dp)
                    .shadow(8.dp, CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bott),
                    contentDescription = "ChatBot",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    }
}

@Composable
fun ChatbotMenuPopup(
    onChatClick: () -> Unit,
    onHelpClick: () -> Unit,
    onAboutClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            BotMenuDialog(
                onChatClick = onChatClick,
                onHelpClick = onHelpClick,
                onAboutClick = onAboutClick,
                onDismiss = onDismiss
            )
        }
    }
}

// Komponen BotMenuDialog tetap sama seperti sebelumnya
@Composable
fun BotMenuDialog(
    onChatClick: () -> Unit,
    onHelpClick: () -> Unit,
    onAboutClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = "Toba Guide AI",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00BCD4)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AI",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Halo! Saya siap membantu Anda\nmenjelajahi keindahan Toba",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            MenuButton(
                icon = Icons.AutoMirrored.Filled.Chat,
                title = "Mulai Chat",
                subtitle = "Tanya apa saja tentang wisata Toba",
                iconColor = Color(0xFF2196F3),
                onClick = onChatClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            MenuButton(
                icon = Icons.Default.Help,
                title = "Bantuan",
                subtitle = "Cara menggunakan AI assistant",
                iconColor = Color(0xFF4CAF50),
                onClick = onHelpClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            MenuButton(
                icon = Icons.Default.Info,
                title = "Tentang AI",
                subtitle = "Informasi tentang Toba Guide AI",
                iconColor = Color(0xFFFF9800),
                onClick = onAboutClick
            )
        }
    }
}
@Composable
fun MenuButton(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TobaGuideAIChat(
    messages: List<ChatMessage> = sampleMessages,
    onSendMessage: (String) -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var messageText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    // Auto scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            scope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00BCD4)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "AI",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Toba Guide AI",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF2196F3),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        // Chat Messages
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(messages) { message ->
                ChatMessageItem(message = message)
            }
        }

        // Message Input
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text(
                            text = "Tulis pesan...",
                            color = Color.Gray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(20.dp)
                )

                IconButton(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            onSendMessage(messageText)
                            messageText = ""
                        }
                    },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2196F3))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isFromUser) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        if (!message.isFromUser) {
            // AI Avatar
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF00BCD4)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AI",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            // Message Bubble
            Card(
                shape = RoundedCornerShape(
                    topStart = if (message.isFromUser) 16.dp else 4.dp,
                    topEnd = if (message.isFromUser) 4.dp else 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if (message.isFromUser) {
                        Color(0xFF2196F3)
                    } else {
                        Color.White
                    }
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = message.message,
                        color = if (message.isFromUser) Color.White else Color.Black,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )

                    // Show recommendations if available
                    if (message.recommendations.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        message.recommendations.forEachIndexed { index, recommendation ->
                            Text(
                                text = "${index + 1}. $recommendation",
                                color = Color.Black,
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Mau detail salah satunya?",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Timestamp
            Text(
                text = message.timestamp,
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.padding(
                    start = if (message.isFromUser) 0.dp else 8.dp,
                    end = if (message.isFromUser) 8.dp else 0.dp,
                    top = 4.dp
                ),
                textAlign = if (message.isFromUser) TextAlign.End else TextAlign.Start
            )
        }

        if (message.isFromUser) {
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

// Helper functions
fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val amPm = if (hour < 12) "AM" else "PM"
    val displayHour = if (hour == 0) 12 else if (hour > 12) hour - 12 else hour
    return String.format(Locale.getDefault(), "%d:%02d %s", displayHour, minute, amPm)
}

fun generateAIResponse(userMessage: String): ChatMessage {
    // Simulate delay for better UX
    Thread.sleep(500)

    val responses = mapOf(
        "wisata" to ChatMessage(
            id = System.currentTimeMillis().toString(),
            message = "Tentu! Berikut 3 rekomendasi untuk wisata di sekitar Toba:",
            isFromUser = false,
            timestamp = getCurrentTime(),
            isRecommendation = true,
            recommendations = listOf(
                "Bukit Holbung - Air Terjun Panorama Toba",
                "Museum Batak TB Silalahi",
                "Air Terjun Situmurun (Binangkulo)"
            )
        ),
        "kuliner" to ChatMessage(
            id = System.currentTimeMillis().toString(),
            message = "Berikut rekomendasi kuliner khas Batak yang wajib dicoba:",
            isFromUser = false,
            timestamp = getCurrentTime(),
            isRecommendation = true,
            recommendations = listOf(
                "Arsik Ikan Mas - Ikan mas dengan bumbu andaliman",
                "Saksang - Daging babi/kerbau dengan bumbu khas",
                "Dali Ni Horbo - Susu kerbau fermentasi"
            )
        ),
        "akomodasi" to ChatMessage(
            id = System.currentTimeMillis().toString(),
            message = "Berikut rekomendasi tempat menginap yang nyaman:",
            isFromUser = false,
            timestamp = getCurrentTime(),
            isRecommendation = true,
            recommendations = listOf(
                "Toba Village Inn - Pemandangan langsung danau",
                "Hotel Niagara Parapat - Fasilitas lengkap",
                "Samosir Villa Resort - Resort eksklusif di Samosir"
            )
        ),
        "default" to ChatMessage(
            id = System.currentTimeMillis().toString(),
            message = "Terima kasih atas pertanyaannya! Saya siap membantu Anda menjelajahi keindahan Danau Toba. Ada yang ingin Anda ketahui tentang wisata, kuliner, atau akomodasi di sekitar Toba?",
            isFromUser = false,
            timestamp = getCurrentTime()
        )
    )

    val lowerMessage = userMessage.lowercase()
    return when {
        lowerMessage.contains("wisata") || lowerMessage.contains("rekomendasi") || lowerMessage.contains("tempat") ->
            responses["wisata"]!!
        lowerMessage.contains("kuliner") || lowerMessage.contains("makanan") || lowerMessage.contains("makan") ->
            responses["kuliner"]!!
        lowerMessage.contains("hotel") || lowerMessage.contains("menginap") || lowerMessage.contains("akomodasi") ->
            responses["akomodasi"]!!
        else -> responses["default"]!!
    }
}

// Sample data for preview
val sampleMessages = listOf(
    ChatMessage(
        id = "1",
        message = "Halo! Selamat datang di Toba Guide AI.\nApa ada yang bisa saya bantu?",
        isFromUser = false,
        timestamp = "10:27 AM"
    ),
    ChatMessage(
        id = "2",
        message = "Bisa rekomendasikan tempat wisata untuk di sekitar Samosir?",
        isFromUser = true,
        timestamp = "10:27 AM"
    ),
    ChatMessage(
        id = "3",
        message = "Tentu! Berikut 3 rekomendasi untuk wisata di sekitar Samosir:",
        isFromUser = false,
        timestamp = "10:28 AM",
        isRecommendation = true,
        recommendations = listOf(
            "Bukit Holbung - Air Terjun Panorama Toba",
            "Museum Batak TB Silalahi",
            "Air Terjun Situmurun (Binangkulo)"
        )
    )
)

@Preview(showBackground = true)
@Composable
fun DraggableChatbotContainerPreview() {
    DraggableChatbotContainer()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TobaGuideHelpScreen(
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Bantuan",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF2196F3),
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White
            )
        )

        // Help Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Welcome Section
            item {
                WelcomeHelpCard()
            }

            // How to Use Section
            item {
                HowToUseCard()
            }

            // Features Section
            item {
                FeaturesCard()
            }

            // Sample Questions Section
            item {
                SampleQuestionsCard()
            }

            // Tips Section
            item {
                TipsCard()
            }

            // FAQ Section
            item {
                FAQCard()
            }

            // Contact Section
            item {
                ContactCard()
            }
        }
    }
}

@Composable
fun WelcomeHelpCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2196F3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Help,
                    contentDescription = "Help",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Selamat Datang di Toba Guide AI",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Asisten virtual yang siap membantu Anda menjelajahi keindahan Danau Toba dan sekitarnya.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun HowToUseCard() {
    HelpSectionCard(
        title = "Cara Menggunakan",
        icon = Icons.Default.PlayArrow,
        iconColor = Color(0xFF4CAF50)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HelpStepItem(
                stepNumber = "1",
                title = "Klik Tombol Chat",
                description = "Tekan tombol 'Mulai Chat' untuk memulai percakapan dengan AI"
            )

            HelpStepItem(
                stepNumber = "2",
                title = "Ajukan Pertanyaan",
                description = "Ketik pertanyaan tentang wisata, kuliner, atau akomodasi di Toba"
            )

            HelpStepItem(
                stepNumber = "3",
                title = "Dapatkan Rekomendasi",
                description = "AI akan memberikan rekomendasi terbaik sesuai kebutuhan Anda"
            )

            HelpStepItem(
                stepNumber = "4",
                title = "Eksplorasi Lebih Lanjut",
                description = "Tanyakan detail lebih lanjut untuk informasi yang lebih spesifik"
            )
        }
    }
}

@Composable
fun FeaturesCard() {
    HelpSectionCard(
        title = "Fitur Utama",
        icon = Icons.Default.Star,
        iconColor = Color(0xFFFF9800)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FeatureItem(
                icon = Icons.Default.Place,
                title = "Rekomendasi Wisata",
                description = "Dapatkan rekomendasi tempat wisata terbaik di sekitar Danau Toba"
            )

            FeatureItem(
                icon = Icons.Default.Restaurant,
                title = "Kuliner Khas Batak",
                description = "Temukan makanan dan minuman khas yang wajib dicoba"
            )

            FeatureItem(
                icon = Icons.Default.Hotel,
                title = "Akomodasi Terbaik",
                description = "Rekomendasi hotel dan penginapan yang nyaman"
            )

            FeatureItem(
                icon = Icons.Default.Navigation,
                title = "Panduan Perjalanan",
                description = "Tips dan panduan untuk perjalanan yang menyenangkan"
            )
        }
    }
}

@Composable
fun SampleQuestionsCard() {
    HelpSectionCard(
        title = "Contoh Pertanyaan",
        icon = Icons.AutoMirrored.Filled.Chat,
        iconColor = Color(0xFF9C27B0)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val sampleQuestions = listOf(
                "Rekomendasikan tempat wisata di Samosir",
                "Makanan khas Batak apa yang wajib dicoba?",
                "Hotel terbaik di sekitar Danau Toba",
                "Bagaimana cara ke Air Terjun Situmurun?",
                "Kapan waktu terbaik berkunjung ke Toba?",
                "Oleh-oleh khas apa yang bisa dibeli?"
            )

            sampleQuestions.forEach { question ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA))
                ) {
                    Text(
                        text = "\"$question\"",
                        modifier = Modifier.padding(12.dp),
                        fontSize = 13.sp,
                        color = Color(0xFF666666),
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
            }
        }
    }
}

@Composable
fun TipsCard() {
    HelpSectionCard(
        title = "Tips Penggunaan",
        icon = Icons.Default.Lightbulb,
        iconColor = Color(0xFFFFC107)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TipItem(
                tip = "Gunakan kata kunci yang spesifik seperti 'wisata', 'kuliner', atau 'hotel'"
            )

            TipItem(
                tip = "Sebutkan lokasi yang diinginkan untuk rekomendasi yang lebih akurat"
            )

            TipItem(
                tip = "Tanyakan detail lebih lanjut jika membutuhkan informasi tambahan"
            )

            TipItem(
                tip = "AI dapat membantu dengan berbagai aspek perjalanan Anda"
            )
        }
    }
}

@Composable
fun FAQCard() {
    HelpSectionCard(
        title = "Pertanyaan Umum",
        icon = Icons.Default.QuestionMark,
        iconColor = Color(0xFF00BCD4)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FAQItem(
                question = "Apakah AI ini gratis?",
                answer = "Ya, Toba Guide AI dapat digunakan secara gratis untuk membantu perjalanan Anda."
            )

            FAQItem(
                question = "Seberapa akurat informasinya?",
                answer = "Informasi yang diberikan berdasarkan data terkini tentang wisata Danau Toba dan sekitarnya."
            )

            FAQItem(
                question = "Bisa tanya dalam bahasa Inggris?",
                answer = "Saat ini AI lebih optimal untuk pertanyaan dalam Bahasa Indonesia."
            )

            FAQItem(
                question = "Bagaimana cara memberikan feedback?",
                answer = "Anda dapat memberikan feedback melalui menu kontak atau langsung dalam chat."
            )
        }
    }
}

@Composable
fun ContactCard() {
    HelpSectionCard(
        title = "Kontak & Dukungan",
        icon = Icons.Default.ContactSupport,
        iconColor = Color(0xFFE91E63)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ContactItem(
                icon = Icons.Default.Email,
                title = "Email",
                description = "support@tobaguide.com"
            )

            ContactItem(
                icon = Icons.Default.Phone,
                title = "WhatsApp",
                description = "+62 812-3456-7890"
            )

            ContactItem(
                icon = Icons.Default.Language,
                title = "Website",
                description = "www.tobaguide.com"
            )
        }
    }
}

@Composable
fun HelpSectionCard(
    title: String,
    icon: ImageVector,
    iconColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(iconColor.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconColor,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            content()
        }
    }
}

@Composable
fun HelpStepItem(
    stepNumber: String,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color(0xFF4CAF50)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = Color.Gray,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun FeatureItem(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFFFF9800),
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun TipItem(tip: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Tip",
            tint = Color(0xFF4CAF50),
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = tip,
            fontSize = 13.sp,
            color = Color(0xFF333333),
            lineHeight = 18.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun FAQItem(
    question: String,
    answer: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Q: $question",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "A: $answer",
            fontSize = 12.sp,
            color = Color.Gray,
            lineHeight = 16.sp
        )
    }
}

@Composable
fun ContactItem(
    icon: ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color(0xFFE91E63),
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TobaGuideHelpScreenPreview() {
    TobaGuideHelpScreen()
}