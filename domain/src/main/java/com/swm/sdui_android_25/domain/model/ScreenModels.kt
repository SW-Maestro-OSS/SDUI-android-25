package com.swm.sdui_android_25.domain.model

data class ScreenResponseDto(
    val screen: ScreenDto
)

data class ScreenDto(
    val id: String,
    val title: String,
    val components: List<ComponentSpec>,
)

data class ModifierSpec(
    val height: Int? = null,
    val width: Int? = null,
    val elevation: Int? = null,
)

enum class ComponentType {
    SPACER,
    TEXT,
    BUTTON,
    IMAGE,
    CARD,
    COLUMN,
    ROW,
}

sealed interface ComponentSpec {
    val id: String
    val type: ComponentType
    val modifierSpec: ModifierSpec? get() = null
}

data class SpacerComponentDto(
    override val id: String,
    override val type: ComponentType = ComponentType.SPACER,
    override val modifierSpec: ModifierSpec? = null,
) : ComponentSpec

enum class FONT_WEIGHT() {
    MEDIUM,
    BOLD,
}

data class TextComponentDto(
    override val id: String,
    override val type: ComponentType = ComponentType.TEXT,
    val text: String = "",
    val fontSize: Int = 16,
    val fontWeight: FONT_WEIGHT? = FONT_WEIGHT.MEDIUM,
    override val modifierSpec: ModifierSpec? = null,
) : ComponentSpec

// 기존 ActionType enum은 유지
enum class ActionType {
    TOAST,
    NAVIGATE,
    DIALOG,
}

// ActionSpec을 sealed interface로 변경
sealed interface ActionSpec {
    val type: ActionType
}

// TOAST 액션
data class ToastActionSpec(
    override val type: ActionType = ActionType.TOAST,
    val message: String
) : ActionSpec

// NAVIGATE 액션
data class NavigateActionSpec(
    override val type: ActionType = ActionType.NAVIGATE,
    val targetScreen: String,
    val parameters: Map<String, Any>? = null
) : ActionSpec

// 다이얼로그 버튼 스펙
data class DialogButtonSpec(
    val text: String,
    val action: ActionSpec? = null,
    val isDismiss: Boolean = false
)

// DIALOG 액션
data class DialogActionSpec(
    override val type: ActionType = ActionType.DIALOG,
    val title: String,
    val message: String,
    val buttons: List<DialogButtonSpec> = emptyList(),
    val isDismissible: Boolean = true
) : ActionSpec

data class ButtonComponentDto(
    override val id: String,
    override val type: ComponentType = ComponentType.BUTTON,
    val text: String? = null,
    val action: ActionSpec? = null,
    override val modifierSpec: ModifierSpec? = null,
) : ComponentSpec

data class ImageComponentDto(
    override val id: String,
    override val type: ComponentType = ComponentType.IMAGE,
    val url: String,
    val contentDescription: String?,
    override val modifierSpec: ModifierSpec? = null,
) : ComponentSpec

sealed interface ContainerSpec : ComponentSpec {
    val children: List<ComponentSpec>
}

data class CardComponentDto(
    override val id: String,
    override val type: ComponentType = ComponentType.CARD,
    override val children: List<ComponentSpec> = emptyList(),
    override val modifierSpec: ModifierSpec? = null,
) : ContainerSpec

data class ColumnComponentDto(
    override val id: String,
    override val type: ComponentType = ComponentType.COLUMN,
    override val children: List<ComponentSpec> = emptyList(),
    override val modifierSpec: ModifierSpec? = null,
) : ContainerSpec

data class RowComponentDto(
    override val id: String,
    override val type: ComponentType = ComponentType.ROW,
    override val children: List<ComponentSpec> = emptyList(),
    override val modifierSpec: ModifierSpec? = null,
) : ContainerSpec 