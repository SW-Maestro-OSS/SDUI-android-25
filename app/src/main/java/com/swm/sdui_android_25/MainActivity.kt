package com.swm.sdui_android_25


// Data imports

// UI imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.swm.sdui_android_25.data.SduiRepositoryImpl
import com.swm.sdui_android_25.data.mapper.ScreenMapper
import com.swm.sdui_android_25.data.network.NetworkModule
import com.swm.sdui_android_25.domain.repository.SduiRepository
import com.swm.sdui_android_25.screens.AScreen
import com.swm.sdui_android_25.screens.BScreen
import com.swm.sdui_android_25.screens.CScreen
import com.swm.sdui_android_25.screens.JsonViewerScreen
import com.swm.sdui_android_25.ui.theme.SDUIandroid25Theme

class MainActivity : ComponentActivity() {
    private val apiService = NetworkModule.provideApiService()
    private val screenRepository: SduiRepository = SduiRepositoryImpl(apiService)
    private val screenMapper = ScreenMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SDUIandroid25Theme {
                MainScreen(
                    screenRepository = screenRepository,
                    screenMapper = screenMapper
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    screenRepository: SduiRepository,
    screenMapper: ScreenMapper
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { },
                    label = { Text("Json") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { },
                    label = { Text("A") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { },
                    label = { Text("B") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                NavigationBarItem(
                    icon = { },
                    label = { Text("C") },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> JsonViewerScreen(
                screenRepository = screenRepository,
                screenMapper = screenMapper,
                modifier = Modifier.padding(innerPadding)
            )
            1 -> AScreen(
                onNavigateToB = { selectedTab = 2 },
                onNavigateToC = { selectedTab = 3 },
                modifier = Modifier.padding(innerPadding)
            )
            2 -> BScreen(
                onNavigateBack = { selectedTab = 0 },
                modifier = Modifier.padding(innerPadding)
            )
            3 -> CScreen(
                onNavigateBack = { selectedTab = 0 },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

