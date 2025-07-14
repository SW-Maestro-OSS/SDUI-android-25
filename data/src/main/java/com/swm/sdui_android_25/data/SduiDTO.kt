package com.swm.sdui_android_25.data

data class ScreenResponseDto(
    val screen: ScreenDto
)

data class ScreenDto(
    val id: String,
    val title: String,
    val components: List<ComponentDto>,
)

data class ComponentDto(
    val type: String,
    val id: String,
    val text: String? = null,
    val action: ActionDto? = null,
    val children: List<ComponentDto>? = null
)

data class ActionDto(
    val type: String,
    val message: String? = null,
)