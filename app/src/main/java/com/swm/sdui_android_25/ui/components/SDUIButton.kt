package com.swm.sdui_android_25.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.swm.sdui_android_25.domain.model.ButtonComponentDto
import com.swm.sdui_android_25.domain.model.ActionType

@Composable
fun SDUIButton(
    component: ButtonComponentDto,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Button(
        onClick = {
            component.action?.let { action ->
                when (action.type) {
                    ActionType.TOAST -> {
                        Toast.makeText(
                            context,
                            action.message ?: "토스트 메시지",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    ActionType.NAVIGATE -> {
                        // 네비게이션 처리
                    }
                    ActionType.DIALOG -> {
                        // 다이얼로그 처리
                    }
                }
            }
        },
        modifier = modifier
    ) {
        Text(component.text ?: "Button")
    }
}