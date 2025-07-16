package com.swm.sdui_android_25.data.network

import com.swm.sdui_android_25.data.api.ApiService
import com.swm.sdui_android_25.data.mock.MockWebServerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private var mockWebServerManager: MockWebServerManager? = null
    private var apiService: ApiService? = null
    
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    
    fun provideApiService(): ApiService {
        if (apiService == null) {
            synchronized(this) {
                if (apiService == null) {
                    mockWebServerManager = MockWebServerManager()
                    val baseUrl = runBlocking(Dispatchers.IO) {
                        mockWebServerManager!!.start()
                    }
                    
                    val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    
                    apiService = retrofit.create(ApiService::class.java)
                }
            }
        }
        return apiService!!
    }

    fun stopMockServer() {
        mockWebServerManager?.stop()
    }
} 
