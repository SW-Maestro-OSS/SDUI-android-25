package com.swm.sdui_android_25.data

import com.swm.sdui_android_25.data.api.ApiService
import com.swm.sdui_android_25.domain.model.Screen
import com.swm.sdui_android_25.domain.repository.ScreenRepository

class ScreenRepositoryImpl(
    private val apiService: ApiService,
    private val mapper: ScreenMapper
) : ScreenRepository {

    override suspend fun getScreen(screenId: String): Result<Screen> {
        return try {
            val response = when (screenId) {
                "home1" -> apiService.getHome1()
                "home2" -> apiService.getHome2()
                else -> throw IllegalArgumentException("Unknown screen: $screenId")
            }

            if (response.isSuccessful && response.body() != null) {
                val screen = mapper.toDomain(response.body()!!)
                Result.success(screen)
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}