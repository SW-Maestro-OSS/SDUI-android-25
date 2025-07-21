package com.swm.sdui_android_25.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swm.sdui_android_25.RenderComponent
import com.swm.sdui_android_25.domain.model.ColumnComponentDto
import com.swm.sdui_android_25.RenderComponent

@Composable
fun SDUIColumn(
    component: ColumnComponentDto,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp) // modifierSpec에서 padding 가져와야 함
    ) {
        component.children.forEach { child ->
            RenderComponent(
                component = child,
                modifier = Modifier
            )
        }
    }
}