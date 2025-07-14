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
import com.swm.sdui_android_25.data.repository.ScreenRepository
import com.swm.sdui_android_25.ui.theme.SDUIandroid25Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val screenRepository = ScreenRepository()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SDUIandroid25Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JsonViewerScreen(
                        screenRepository = screenRepository,
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
    screenRepository: ScreenRepository,
    modifier: Modifier = Modifier
) {
    var jsonResponse by remember { mutableStateOf("버튼을 클릭하여 JSON 응답을 확인하세요.") }
    var isLoading by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 제목
        Text(
            text = "MockWebServer JSON 응답 테스트",
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
                    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                        screenRepository.getHome1Screen()
                            .onSuccess { response ->
                                jsonResponse = response
                                isLoading = false
                            }
                            .onFailure { exception ->
                                jsonResponse = "Error: ${exception.message}"
                                isLoading = false
                            }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.weight(1f)
            ) {
                Text("Home1 요청")
            }
            
            Button(
                onClick = {
                    isLoading = true
                    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
                        screenRepository.getHome2Screen()
                            .onSuccess { response ->
                                jsonResponse = response
                                isLoading = false
                            }
                            .onFailure { exception ->
                                jsonResponse = "Error: ${exception.message}"
                                isLoading = false
                            }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.weight(1f)
            ) {
                Text("Home2 요청")
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
        
        // JSON 응답 표시 영역
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