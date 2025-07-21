package com.swm.sdui_android_25.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.swm.sdui_android_25.domain.model.TextComponentDto
import com.swm.sdui_android_25.domain.model.FONT_WEIGHT

@Composable
fun SDUIText(
    component: TextComponentDto,
    modifier: Modifier = Modifier
) {
    Text(
        text = component.text,
        fontSize = if (component.fontSize > 0) component.fontSize.sp else 16.sp,
        fontWeight = when (component.fontWeight ?: FONT_WEIGHT.MEDIUM) {
            FONT_WEIGHT.BOLD -> FontWeight.Bold
            FONT_WEIGHT.MEDIUM -> FontWeight.Medium
        },
        modifier = modifier
    )
}