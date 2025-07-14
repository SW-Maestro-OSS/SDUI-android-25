package com.swm.sdui_android_25.data.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("home1")
    suspend fun getHome1(): Response<String>
    
    @GET("home2")
    suspend fun getHome2(): Response<String>
} 