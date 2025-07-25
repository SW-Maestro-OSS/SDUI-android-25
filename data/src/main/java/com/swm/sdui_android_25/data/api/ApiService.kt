package com.swm.sdui_android_25.data.api

import com.swm.sdui_android_25.domain.model.ScreenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("home")
    suspend fun getHome(): Response<ScreenResponseDto>
    
    @GET("ascreen_sdui")
    suspend fun getAScreen(): Response<ScreenResponseDto>
    
    @GET("bscreen_sdui")
    suspend fun getBScreen(): Response<ScreenResponseDto>
    
    @GET("cscreen_sdui")
    suspend fun getCScreen(): Response<ScreenResponseDto>
}