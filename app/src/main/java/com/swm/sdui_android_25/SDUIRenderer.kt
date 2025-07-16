package com.swm.sdui_android_25

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swm.sdui_android_25.domain.model.Action
import com.swm.sdui_android_25.domain.model.ComponentType
import com.swm.sdui_android_25.domain.model.Screen

@Composable
fun SDUIRenderer(
    screen: Screen,
    onAction: (Action) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = screen.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        screen.components.forEach { component ->
            when (component.type) {
                ComponentType.TEXT -> {
                    Text(
                        text = component.text ?: "",
                        modifier = Modifier.padding(vertical = 4.dp),
                    )
                }
                ComponentType.BUTTON -> {
                    Button(
                        onClick = {
                            component.action?.let { onAction(it) }
                        },
                        modifier = Modifier.padding(vertical = 4.dp),
                    ) {
                        Text(component.text ?: "Button")
                    }
                }
                else -> {}
            }
        }
    }
}
