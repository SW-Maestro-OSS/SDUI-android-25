package com.swm.sdui_android_25.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swm.sdui_android_25.data.RowComponentDto
import com.swm.sdui_android_25.RenderComponent

@Composable
fun SDUIRow(
    component: RowComponentDto,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically, // 기본값은 CenterVertically
    ) {
        component.children.forEach { child ->
            RenderComponent(
                component = child,
                modifier = Modifier
            )
        }
    }
}