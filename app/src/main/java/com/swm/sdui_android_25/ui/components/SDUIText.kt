package com.swm.sdui_android_25.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.swm.sdui_android_25.data.TextComponentDto
import com.swm.sdui_android_25.data.FONT_WEIGHT

@Composable
fun SDUIText(
    component: TextComponentDto,
    modifier: Modifier = Modifier
) {
    Text(
        text = component.text,
        fontSize = component.fontSize.sp,
        fontWeight = when (component.fontWeight) {
            FONT_WEIGHT.BOLD -> FontWeight.Bold
            FONT_WEIGHT.MEDIUM -> FontWeight.Medium
        },
        modifier = modifier
    )
}