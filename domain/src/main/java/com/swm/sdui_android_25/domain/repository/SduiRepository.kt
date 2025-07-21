package com.swm.sdui_android_25.domain.repository

import com.swm.sdui_android_25.domain.model.ScreenResponseDto

interface SduiRepository {
    suspend fun getScreen(screenId: String): Result<ScreenResponseDto>
}