package com.swm.sdui_android_25.data

data class ScreenResponseDto(
    val screen: ScreenDto
)

data class ScreenDto(
    val id: String,
    val title: String,
    val components: List<OldComponentDto>,
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

sealed interface ComponentSpec {
    val id: String
    val type: String
    val modifierSpec: ModifierSpec? get() = null
}

data class SpacerComponentDto(
    override val id: String,
    override val type: String = "Spacer",
) : ComponentSpec


enum class FONT_WEIGHT {
    MEDIUM,
    BOLD,
}

data class TextComponentDto(
    override val id: String,
    override val type: String = "Text",
    val text: String,
    val fontSize: Int = 16,
    val fontWeight: FONT_WEIGHT = FONT_WEIGHT.MEDIUM,
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
    override val type: String = "Button",
    val text: String? = null,
    val action: ActionSpec? = null,
) : ComponentSpec



data class ImageComponentDto(
    override val id: String,
    override val type: String = "Image",
    val url: String,
    val contentDescription: String?,
) : ComponentSpec

sealed interface ContainerSpec : ComponentSpec {
    val children: List<ComponentSpec>
}

data class CardComponentDto(
    override val id: String,
    override val type: String = "Card",
    override val children: List<ComponentSpec> = emptyList(),
) : ContainerSpec

data class ColumnComponentDto(
    override val id: String,
    override val type: String = "Column",
    override val children: List<ComponentSpec> = emptyList(),
) : ContainerSpec

data class RowComponentDto(
    override val id: String,
    override val type: String = "Row",
    override val children: List<ComponentSpec> = emptyList(),
) : ContainerSpec