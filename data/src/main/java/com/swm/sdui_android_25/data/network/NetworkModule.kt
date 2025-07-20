package com.swm.sdui_android_25.data.network

import com.google.gson.GsonBuilder
import com.swm.sdui_android_25.data.ActionType
import com.swm.sdui_android_25.data.ActionTypeAdapter
import com.swm.sdui_android_25.data.ComponentSpec
import com.swm.sdui_android_25.data.FONT_WEIGHT
import com.swm.sdui_android_25.data.FontWeightAdapter
import com.swm.sdui_android_25.data.ViewAdapter
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
    
    private val gson = GsonBuilder()
        .registerTypeAdapter(ComponentSpec::class.java, ViewAdapter())
        .registerTypeAdapter(FONT_WEIGHT::class.java, FontWeightAdapter())
        .registerTypeAdapter(ActionType::class.java, ActionTypeAdapter())
        .create()
    
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
                        .addConverterFactory(GsonConverterFactory.create(gson))
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
