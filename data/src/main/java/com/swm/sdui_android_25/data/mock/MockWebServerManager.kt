package com.swm.sdui_android_25.data.mock

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerManager {
    private val mockWebServer = MockWebServer()
    
    fun start(): String {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse =
                when (request.path) {
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

        mockWebServer.start()
        return mockWebServer.url("/").toString()
    }
    
    fun stop() {
        mockWebServer.shutdown()
    }

    private fun getHome1Response(): String =
        """
        {
          "screen": {
            "id": "test_screen",
            "title": "테스트 화면",
            "components": [
              {
                "type": "Text",
                "id": "greeting_text",
                "text": "안녕하세요, 서버 드리븐 UI!1"
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

    private fun getHome2Response(): String =
        """
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

    private fun getHomeAdvancedResponse(): String =
        """
        {
          "screen": {
            "id": "home_advanced",
            "title": "어드밴스드 홈",
            "components": [
              {
                "type": "COLUMN",
                "id": "root_column",
                "modifier": {
                  "padding": 16
                },
                "children": [
                  {
                    "type": "TEXT",
                    "id": "title_text",
                    "text": "오늘의 추천 상품",
                    "fontSize": 24,
                    "fontWeight": "BOLD"
                  },
                  {
                    "type": "SPACER",
                    "id": "title_spacer",
                    "modifier": {
                      "height": 16
                    }
                  },
                  {
                    "type": "CARD",
                    "id": "product_card_1",
                    "modifier": {
                      "elevation": 4
                    },
                    "children": [
                      {
                        "type": "ROW",
                        "id": "card_content_row",
                        "modifier": {
                          "padding": 16
                        },
                        "verticalAlignment": "CENTER",
                        "children": [
                          {
                            "type": "IMAGE",
                            "id": "product_image",
                            "url": "https://picsum.photos/200",
                            "contentDescription": "샘플 상품 이미지",
                            "modifier": {
                              "size": 80
                            }
                          },
                          {
                            "type": "SPACER",
                            "id": "image_spacer",
                            "modifier": {
                              "width": 16
                            }
                          },
                          {
                            "type": "COLUMN",
                            "id": "product_info_column",
                            "modifier": {
                               "weight": 1
                            },
                            "children": [
                               {
                                 "type": "TEXT",
                                 "id": "product_name",
                                 "text": "서버에서 온 신선한 과일",
                                 "fontSize": 18,
                                 "fontWeight": "MEDIUM"
                               },
                               {
                                 "type": "TEXT",
                                 "id": "product_description",
                                 "text": "매우 달고 맛있어요. 서버 직송 상품입니다."
                               }
                            ]
                          }
                        ]
                      }
                    ]
                  },
                  {
                    "type": "SPACER",
                    "id": "card_spacer",
                    "modifier": {
                      "height": 24
                    }
                  },
                  {
                    "type": "BUTTON",
                    "id": "show_toast_button",
                    "text": "토스트 메시지 보기",
                    "action": {
                      "type": "TOAST",
                      "message": "서버가 보낸 토스트입니다!"
                    }
                  }
                ]
              }
            ]
          }
        }
        """.trimIndent()
} 
