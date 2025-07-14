package com.swm.sdui_android_25.domain.model

data class Screen(
    val id: String,
    val title: String,
    val components: List<Component>
)

data class Component(
    val type: String,
    val id: String,
    val text: String?,
    val action: Action?
)

data class Action(
    val type: String,
    val message: String?
)

data class ScreenResponse(
    val screen: Screen
) 