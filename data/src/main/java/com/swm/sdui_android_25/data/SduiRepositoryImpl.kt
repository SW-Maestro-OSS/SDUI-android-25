package com.swm.sdui_android_25.data

import com.swm.sdui_android_25.data.api.ApiService
import com.swm.sdui_android_25.domain.model.ScreenResponseDto
import com.swm.sdui_android_25.domain.repository.SduiRepository

class SduiRepositoryImpl(
    private val apiService: ApiService
) : SduiRepository {

    override suspend fun getScreen(screenId: String): Result<ScreenResponseDto> {
        return try {
            val response = when (screenId) {
                "home" -> apiService.getHome()
                "ascreen_sdui" -> apiService.getAScreen()
                "bscreen_sdui" -> apiService.getBScreen()
                "cscreen_sdui" -> apiService.getCScreen()
                else -> throw IllegalArgumentException("Unknown screen: $screenId")
            }

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}