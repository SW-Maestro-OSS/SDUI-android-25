package com.swm.sdui_android_25.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swm.sdui_android_25.data.SpacerComponentDto

@Composable
fun SDUISpacer(
    component: SpacerComponentDto,
    modifier: Modifier = Modifier
) {

    Spacer(
        modifier = modifier
            .height(16.dp) // 기본값, 실제로는 modifierSpec에서 가져와야 함
            .width(16.dp)
    )
}