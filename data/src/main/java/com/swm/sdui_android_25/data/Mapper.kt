package com.swm.sdui_android_25.data

import com.swm.sdui_android_25.domain.model.Action
import com.swm.sdui_android_25.domain.model.ActionType
import com.swm.sdui_android_25.domain.model.Component
import com.swm.sdui_android_25.domain.model.ComponentType
import com.swm.sdui_android_25.domain.model.Screen

class ScreenMapper {
    fun toDomain(dto: ScreenResponseDto): Screen {
        return Screen(
            id = dto.screen.id,
            title = dto.screen.title,
            components = dto.screen.components.map { ComponentMapper().toDomain(it) }
        )
    }
    fun toDto(domain: Screen): ScreenResponseDto {
        return ScreenResponseDto(
            screen = ScreenDto(
                id = domain.id,
                title = domain.title,
                components = domain.components.map { ComponentMapper().toDto(it) }
            )
        )
    }
}

class ComponentMapper {
    fun toDomain(dto: ComponentDto): Component {
        return Component(
            type = ComponentType.valueOf(dto.type.uppercase()),
            id = dto.id,
            text = dto.text,
            action = dto.action?.let {
                Action(
                    type = ActionType.valueOf(it.type.uppercase()),
                    message = it.message
                )
            },
            children = dto.children?.map { toDomain(it) } ?: emptyList()
        )
    }
    fun toDto(domain: Component): ComponentDto {
        return ComponentDto(
            type = domain.type.name.lowercase(),
            id = domain.id,
            text = domain.text,
            action = domain.action?.let {
                ActionDto(
                    type = it.type.name.lowercase(),
                    message = it.message
                )
            },
            children = if (domain.children.isNotEmpty()) {
                domain.children.map { toDto(it) }
            } else null
        )
    }
}