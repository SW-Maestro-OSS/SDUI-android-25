package com.swm.sdui_android_25.data.api

import com.swm.sdui_android_25.data.ActionDto
import com.swm.sdui_android_25.data.ScreenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("home1")
    suspend fun getHome1(): Response<ScreenResponseDto>
    
    @GET("home2")
    suspend fun getHome2(): Response<ScreenResponseDto>

}