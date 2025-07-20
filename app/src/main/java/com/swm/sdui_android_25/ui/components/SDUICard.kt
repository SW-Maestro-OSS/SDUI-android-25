package com.swm.sdui_android_25.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swm.sdui_android_25.data.CardComponentDto
import com.swm.sdui_android_25.RenderComponent

@Composable
fun SDUICard(
    component: CardComponentDto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // modifierSpec에서 elevation 가져와야 함
    ) {
        Column {
            component.children.forEach { child ->
                RenderComponent(
                    component = child,
                    modifier = Modifier
                )
            }
        }
    }
}