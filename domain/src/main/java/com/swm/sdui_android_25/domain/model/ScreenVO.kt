package com.swm.sdui_android_25.domain.model

// 기존 ScreenVO.kt 확장
data class Screen(
    val id: String,
    val title: String,
    val components: List<Component>,
)

data class Component(
    val type: ComponentType,
    val id: String,
    val text: String? = null,
    val children: List<Component> = emptyList(),
    val action: Action? = null
)

enum class ComponentType {
    TEXT, BUTTON, IMAGE, COLUMN, ROW, CARD, INPUT, LOADING
}

data class Action(
    val type: ActionType,
    val message: String? = null,
)

enum class ActionType {
    NAVIGATE, TOAST, API_CALL, DIALOG, UPDATE_COMPONENT
}