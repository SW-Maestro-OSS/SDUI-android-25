package com.swm.sdui_android_25.data.repository

import com.swm.sdui_android_25.data.ScreenResponseDto
import com.swm.sdui_android_25.data.api.ApiService
import com.swm.sdui_android_25.data.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScreenRepository {
    private val apiService: ApiService = NetworkModule.provideApiService()
    
    suspend fun getHome1Screen(): Result<ScreenResponseDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getHome1()
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
    
    suspend fun getHome2Screen(): Result<ScreenResponseDto> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getHome2()
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
} 