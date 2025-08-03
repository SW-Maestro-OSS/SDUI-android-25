package com.swm.sdui_android_25.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.swm.sdui_android_25.domain.model.ButtonComponentDto
import com.swm.sdui_android_25.domain.model.ActionType
import com.swm.sdui_android_25.domain.model.ActionSpec
import com.swm.sdui_android_25.domain.model.ToastActionSpec
import com.swm.sdui_android_25.domain.model.NavigateActionSpec
import com.swm.sdui_android_25.domain.model.DialogActionSpec

@Composable
fun SDUIButton(
    component: ButtonComponentDto,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Button(
        onClick = {
            component.action?.let { action ->
                when (action) {
                    is ToastActionSpec -> {
                        Toast.makeText(
                            context,
                            action.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is NavigateActionSpec -> {
                        // 네비게이션 처리
                        // TODO: 네비게이션 구현
                    }
                    is DialogActionSpec -> {
                        // 다이얼로그 처리
                        // TODO: 다이얼로그 구현
                    }
                }
            }
        },
        modifier = modifier
    ) {
        Text(component.text ?: "Button")
    }
}