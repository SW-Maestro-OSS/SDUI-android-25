package com.swm.sdui_android_25.data.mapper

import com.swm.sdui_android_25.domain.model.ScreenResponseDto
import com.swm.sdui_android_25.data.network.GsonProvider

class ScreenMapper {

    fun fromJson(json: String): ScreenResponseDto {
        return try {
            GsonProvider.gson.fromJson(json, ScreenResponseDto::class.java)
        } catch (e: Exception) {
            throw Exception("Failed to parse screen JSON: ${e.message}", e)
        }
    }

    fun toJson(screenResponse: ScreenResponseDto): String {
        return GsonProvider.gson.toJson(screenResponse)
    }
}