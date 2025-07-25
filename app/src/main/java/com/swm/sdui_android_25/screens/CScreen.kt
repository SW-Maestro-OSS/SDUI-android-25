package com.swm.sdui_android_25.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

// Data imports
import com.swm.sdui_android_25.domain.repository.SduiRepository
import com.swm.sdui_android_25.data.mapper.ScreenMapper
import com.swm.sdui_android_25.domain.model.ScreenResponseDto
import com.swm.sdui_android_25.SDUIRenderer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CScreen(
    screenRepository: SduiRepository,
    screenMapper: ScreenMapper,
    modifier: Modifier = Modifier
) {
    var jsonResponse by remember { mutableStateOf("버튼을 클릭하여 C 화면 JSON 응답을 확인하세요.") }
    var currentScreen by remember { mutableStateOf<ScreenResponseDto?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 제목
        Text(
            text = "C 화면 - Server Driven UI 테스트",
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
                    coroutineScope.launch {
                        screenRepository.getScreen("cscreen_sdui")
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
                Text("C 화면 SDUI 로드")
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