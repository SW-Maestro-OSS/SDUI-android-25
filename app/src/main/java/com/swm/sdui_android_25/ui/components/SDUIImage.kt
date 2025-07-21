package com.swm.sdui_android_25.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.swm.sdui_android_25.domain.model.ImageComponentDto

@Composable
fun SDUIImage(
    component: ImageComponentDto,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(component.url)
            .crossfade(true)
            .build(),
        contentDescription = component.contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier.size(80.dp) // modifierSpec에서 size 가져와야 함
    )
}