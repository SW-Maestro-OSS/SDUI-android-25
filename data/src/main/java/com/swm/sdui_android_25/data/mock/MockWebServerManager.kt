package com.swm.sdui_android_25.data.mock

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerManager {
    private val mockWebServer = MockWebServer()
    
    fun start(): String {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/home1" -> MockResponse()
                        .setResponseCode(200)
                        .setBody(getHome1Response())
                        .addHeader("Content-Type", "application/json")
                    
                    "/home2" -> MockResponse()
                        .setResponseCode(200)
                        .setBody(getHome2Response())
                        .addHeader("Content-Type", "application/json")
                    
                    else -> MockResponse()
                        .setResponseCode(404)
                        .setBody("Not Found")
                }
            }
        }
        
        mockWebServer.start()
        return mockWebServer.url("/").toString()
    }
    
    fun stop() {
        mockWebServer.shutdown()
    }
    
    private fun getHome1Response(): String {
        return """
            {
              "screen": {
                "id": "test_screen",
                "title": "테스트 화면",
                "components": [
                  {
                    "type": "Text",
                    "id": "greeting_text",
                    "text": "안녕하세요, 서버 드리븐 UI!"
                  },
                  {
                    "type": "Button",
                    "id": "click_button",
                    "text": "클릭해 주세요",
                    "action": {
                      "type": "toast",
                      "message": "버튼이 클릭되었습니다!"
                    }
                  }
                ]
              }
            }
        """.trimIndent()
    }
    
    private fun getHome2Response(): String {
        return """
            {
              "screen": {
                "id": "test_screen",
                "title": "테스트 화면",
                "components": [
                  {
                    "type": "Button",
                    "id": "click_button",
                    "text": "클릭해 주세요",
                    "action": {
                      "type": "toast",
                      "message": "버튼이 클릭되었습니다!"
                    }
                  },
                  {
                    "type": "Text",
                    "id": "greeting_text",
                    "text": "안녕하세요, 서버 드리븐 UI!2"
                  }
                ]
              }
            }
        """.trimIndent()
    }
} 