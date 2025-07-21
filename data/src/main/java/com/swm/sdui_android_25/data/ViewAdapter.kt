package com.swm.sdui_android_25.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.swm.sdui_android_25.domain.model.*
import java.lang.reflect.Type

class ViewAdapter : JsonDeserializer<ComponentSpec> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ComponentSpec {
        val jsonObject = json.asJsonObject

        // 필수 필드 추출
        val id = jsonObject.get("id")?.asString
            ?: throw JsonParseException("Component id is required")
        val type = jsonObject.get("type")?.asString?.uppercase()
            ?: throw JsonParseException("Component type is required")

        val componentType = try {
            ComponentType.valueOf(type)
        } catch (e: IllegalArgumentException) {
            // 알 수 없는 타입은 빈 Spacer로 처리하여 안전하게 렌더링
            return SpacerComponentDto(id = id, type = ComponentType.SPACER)
        }

        return when(componentType) {
            ComponentType.SPACER -> context.deserialize(json, SpacerComponentDto::class.java)
            ComponentType.TEXT -> context.deserialize(json, TextComponentDto::class.java)
            ComponentType.BUTTON -> context.deserialize(json, ButtonComponentDto::class.java)
            ComponentType.IMAGE -> context.deserialize(json, ImageComponentDto::class.java)
            ComponentType.CARD -> context.deserialize(json, CardComponentDto::class.java)
            ComponentType.COLUMN -> context.deserialize(json, ColumnComponentDto::class.java)
            ComponentType.ROW -> context.deserialize(json, RowComponentDto::class.java)
        }
    }
}

class FontWeightAdapter : JsonDeserializer<FONT_WEIGHT> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): FONT_WEIGHT {
        return try {
            FONT_WEIGHT.valueOf(json.asString.uppercase())
        } catch (e: Exception) {
            FONT_WEIGHT.MEDIUM
        }
    }
}

class ActionTypeAdapter : JsonDeserializer<ActionType> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ActionType {
        return try {
            ActionType.valueOf(json.asString.uppercase())
        } catch (e: Exception) {
            ActionType.TOAST
        }
    }
}