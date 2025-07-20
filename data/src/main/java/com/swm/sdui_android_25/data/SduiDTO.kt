package com.swm.sdui_android_25.data

data class ScreenResponseDto(
    val screen: ScreenDto
)

data class ScreenDto(
    val id: String,
    val title: String,
    val components: List<ComponentSpec>,
)

data class OldComponentDto(
    val type: String,
    val id: String,
    val text: String? = null,
    val action: ActionDto? = null,
    val children: List<OldComponentDto>? = null
)

data class ActionDto(
    val type: String,
    val message: String? = null,
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
    UNKNOWN,
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
    val fontWeight: FONT_WEIGHT = FONT_WEIGHT.MEDIUM,
    override val modifierSpec: ModifierSpec? = null,
) : ComponentSpec

enum class ActionType {
    TOAST,
    NAVIGATE,
    DIALOG,
}

data class ActionSpec(
    val type: ActionType,
    val message: String? = null,
)

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