package com.swm.sdui_android_25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

// Data imports
import com.swm.sdui_android_25.domain.repository.SduiRepository
import com.swm.sdui_android_25.data.SduiRepositoryImpl
import com.swm.sdui_android_25.data.api.ApiService
import com.swm.sdui_android_25.data.network.NetworkModule
import com.swm.sdui_android_25.domain.model.ScreenResponseDto
import com.swm.sdui_android_25.data.mapper.ScreenMapper

// UI imports
import com.swm.sdui_android_25.ui.theme.SDUIandroid25Theme
import com.swm.sdui_android_25.SDUIRenderer
import kotlin.onSuccess

class MainActivity : ComponentActivity() {
    private val apiService = NetworkModule.provideApiService()
    private val screenRepository: SduiRepository = SduiRepositoryImpl(apiService)
    private val screenMapper = ScreenMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SDUIandroid25Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JsonViewerScreen(
                        screenRepository = screenRepository,
                        screenMapper = screenMapper,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JsonViewerScreen(
    screenRepository: SduiRepository,
    screenMapper: ScreenMapper,
    modifier: Modifier = Modifier
) {
    var jsonResponse by remember { mutableStateOf("버튼을 클릭하여 JSON 응답을 확인하세요.") }
    var currentScreen by remember { mutableStateOf<ScreenResponseDto?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 제목
        Text(
            text = "Server Driven UI 테스트",
            style = MaterialTheme.typography.headlineMedium
        )

        // 버튼들
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    isLoading = true
                    error = null
                    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                        screenRepository.getScreen("home")
                            .onSuccess { response ->
                                currentScreen = response
                                jsonResponse = screenMapper.toJson(response)
                                isLoading = false
                            }
                            .onFailure { exception ->
                                jsonResponse = "Error: ${exception.message}"
                                error = exception.message
                                currentScreen = null
                                isLoading = false
                            }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.weight(1f)
            ) {
                Text("Home SDUI")
            }
        }

        // 로딩 표시
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // 에러 표시
        error?.let { errorMessage ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = "오류: $errorMessage",
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        // SDUI 렌더링 영역
        currentScreen?.let { screenDto ->
            Card(
                modifier = Modifier.weight(1f)
            ) {
                SDUIRenderer(
                    screen = screenDto.screen
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // JSON 응답 표시 영역 (선택적)
        if (currentScreen == null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = "JSON 응답:",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = jsonResponse,
                        fontFamily = FontFamily.Monospace,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}