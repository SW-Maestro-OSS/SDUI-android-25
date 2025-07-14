package com.swm.sdui_android_25.domain.repository

import com.swm.sdui_android_25.domain.model.Action
import com.swm.sdui_android_25.domain.model.Screen

interface ScreenRepository {
    suspend fun getScreen(screenId: String): Result<Screen>
}

//interface ActionRepository {
//    suspend fun executeAction(action: Action): Result<Any>
//}