package com.swm.sdui_android_25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.swm.sdui_android_25.data.ScreenMapper
import com.swm.sdui_android_25.data.ScreenRepositoryImpl
import com.swm.sdui_android_25.data.network.NetworkModule
import com.swm.sdui_android_25.data.repository.ScreenRepository
import com.swm.sdui_android_25.domain.model.ActionType
import com.swm.sdui_android_25.domain.model.Screen
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
                    SDUITestScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun SDUITestScreen(modifier: Modifier) {
    var currentScreen by remember { mutableStateOf<Screen?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val screenRepository =
        remember {
            ScreenRepositoryImpl(
                NetworkModule.provideApiService(),
                ScreenMapper(),
            )
        }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = {
                    isLoading = true
                    error = null
                    coroutineScope.launch {
                        screenRepository
                            .getScreen("home1")
                            .onSuccess { screen ->
                                currentScreen = screen
                                isLoading = false
                            }.onFailure { exception ->
                                error = exception.message
                                isLoading = false
                            }
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text("Home1 SDUI")
            }

            Button(
                onClick = {
                    isLoading = true
                    error = null
                    coroutineScope.launch {
                        screenRepository
                            .getScreen("home2")
                            .onSuccess { screen ->
                                currentScreen = screen
                                isLoading = false
                            }.onFailure { exception ->
                                error = exception.message
                                isLoading = false
                            }
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                Text("Home2 SDUI")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                )
            }

            currentScreen != null -> {
                Card {
                    SDUIRenderer(
                        screen = currentScreen!!,
                        onAction = { action ->
                            if (action.type == ActionType.TOAST) {
                                // 토스트 표시 로직
                            }
                        },
                    )
                }
            }

            else -> {
                Text("버튼을 클릭하여 SDUI를 테스트하세요.")
            }
        }
    }
}
