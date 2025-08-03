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
                    "/home" -> MockResponse()
                        .setResponseCode(200)
                        .setBody(getHomeResponse())
                        .addHeader("Content-Type", "application/json")
                    
                    "/ascreen_sdui" -> MockResponse()
                        .setResponseCode(200)
                        .setBody(getAScreenResponse())
                        .addHeader("Content-Type", "application/json")
                    
                    "/bscreen_sdui" -> MockResponse()
                        .setResponseCode(200)
                        .setBody(getBScreenResponse())
                        .addHeader("Content-Type", "application/json")
                    
                    "/cscreen_sdui" -> MockResponse()
                        .setResponseCode(200)
                        .setBody(getCScreenResponse())
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

    private fun getHomeResponse(): String =
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

    private fun getAScreenResponse(): String =
        """
        {
          "screen": {
            "id": "a_screen",
            "title": "A 화면",
            "components": [
              {
                "type": "COLUMN",
                "id": "a_root_column",
                "modifier": {
                  "padding": 20
                },
                "children": [
                  {
                    "type": "TEXT",
                    "id": "a_title",
                    "text": "A 화면에 오신 것을 환영합니다",
                    "fontSize": 28,
                    "fontWeight": "BOLD"
                  },
                  {
                    "type": "SPACER",
                    "id": "a_title_spacer",
                    "modifier": {
                      "height": 24
                    }
                  },
                  {
                    "type": "CARD",
                    "id": "a_info_card",
                    "modifier": {
                      "elevation": 8,
                      "padding": 16
                    },
                    "children": [
                      {
                        "type": "TEXT",
                        "id": "a_info_text",
                        "text": "이것은 A 화면의 정보 카드입니다. 다양한 컴포넌트들을 테스트할 수 있어요.",
                        "fontSize": 16
                      }
                    ]
                  },
                  {
                    "type": "SPACER",
                    "id": "a_card_spacer",
                    "modifier": {
                      "height": 20
                    }
                  },
                  {
                    "type": "ROW",
                    "id": "a_button_row",
                    "modifier": {
                      "padding": 8
                    },
                    "children": [
                      {
                        "type": "BUTTON",
                        "id": "a_primary_button",
                        "text": "주요 액션",
                        "action": {
                          "type": "TOAST",
                          "message": "A 화면에서 주요 액션이 실행되었습니다!"
                        }
                      },
                      {
                        "type": "SPACER",
                        "id": "a_button_spacer",
                        "modifier": {
                          "width": 12
                        }
                      },
                      {
                        "type": "BUTTON",
                        "id": "a_secondary_button",
                        "text": "보조 액션",
                        "action": {
                          "type": "TOAST",
                          "message": "A 화면에서 보조 액션이 실행되었습니다!"
                        }
                      }
                    ]
                  }
                ]
              }
            ]
          }
        }
        """.trimIndent()

    private fun getBScreenResponse(): String =
        """
        {
          "screen": {
            "id": "b_screen",
            "title": "B 화면",
            "components": [
              {
                "type": "COLUMN",
                "id": "b_root_column",
                "modifier": {
                  "padding": 16
                },
                "children": [
                  {
                    "type": "TEXT",
                    "id": "b_header",
                    "text": "B 화면 - 상품 목록",
                    "fontSize": 24,
                    "fontWeight": "BOLD"
                  },
                  {
                    "type": "SPACER",
                    "id": "b_header_spacer",
                    "modifier": {
                      "height": 20
                    }
                  },
                  {
                    "type": "CARD",
                    "id": "b_product_1",
                    "modifier": {
                      "elevation": 4,
                      "margin": {
                        "bottom": 12
                      }
                    },
                    "children": [
                      {
                        "type": "ROW",
                        "id": "b_product_1_row",
                        "modifier": {
                          "padding": 16
                        },
                        "children": [
                          {
                            "type": "IMAGE",
                            "id": "b_product_1_image",
                            "url": "https://picsum.photos/150/150?random=1",
                            "contentDescription": "상품 1 이미지",
                            "modifier": {
                              "size": 80
                            }
                          },
                          {
                            "type": "SPACER",
                            "id": "b_product_1_spacer",
                            "modifier": {
                              "width": 16
                            }
                          },
                          {
                            "type": "COLUMN",
                            "id": "b_product_1_info",
                            "modifier": {
                              "weight": 1
                            },
                            "children": [
                              {
                                "type": "TEXT",
                                "id": "b_product_1_name",
                                "text": "프리미엄 커피",
                                "fontSize": 18,
                                "fontWeight": "MEDIUM"
                              },
                              {
                                "type": "TEXT",
                                "id": "b_product_1_price",
                                "text": "₩15,000",
                                "fontSize": 16,
                                "fontWeight": "BOLD"
                              }
                            ]
                          },
                          {
                            "type": "BUTTON",
                            "id": "b_product_1_button",
                            "text": "구매",
                            "action": {
                              "type": "TOAST",
                              "message": "프리미엄 커피를 구매하셨습니다!"
                            }
                          }
                        ]
                      }
                    ]
                  },
                  {
                    "type": "CARD",
                    "id": "b_product_2",
                    "modifier": {
                      "elevation": 4,
                      "margin": {
                        "bottom": 12
                      }
                    },
                    "children": [
                      {
                        "type": "ROW",
                        "id": "b_product_2_row",
                        "modifier": {
                          "padding": 16
                        },
                        "children": [
                          {
                            "type": "IMAGE",
                            "id": "b_product_2_image",
                            "url": "https://picsum.photos/150/150?random=2",
                            "contentDescription": "상품 2 이미지",
                            "modifier": {
                              "size": 80
                            }
                          },
                          {
                            "type": "SPACER",
                            "id": "b_product_2_spacer",
                            "modifier": {
                              "width": 16
                            }
                          },
                          {
                            "type": "COLUMN",
                            "id": "b_product_2_info",
                            "modifier": {
                              "weight": 1
                            },
                            "children": [
                              {
                                "type": "TEXT",
                                "id": "b_product_2_name",
                                "text": "유기농 차",
                                "fontSize": 18,
                                "fontWeight": "MEDIUM"
                              },
                              {
                                "type": "TEXT",
                                "id": "b_product_2_price",
                                "text": "₩8,500",
                                "fontSize": 16,
                                "fontWeight": "BOLD"
                              }
                            ]
                          },
                          {
                            "type": "BUTTON",
                            "id": "b_product_2_button",
                            "text": "구매",
                            "action": {
                              "type": "TOAST",
                              "message": "유기농 차를 구매하셨습니다!"
                            }
                          }
                        ]
                      }
                    ]
                  }
                ]
              }
            ]
          }
        }
        """.trimIndent()

    private fun getCScreenResponse(): String =
        """
        {
          "screen": {
            "id": "c_screen",
            "title": "C 화면",
            "components": [
              {
                "type": "COLUMN",
                "id": "c_root_column",
                "modifier": {
                  "padding": 24
                },
                "children": [
                  {
                    "type": "TEXT",
                    "id": "c_welcome",
                    "text": "C 화면에 오신 것을 환영합니다!",
                    "fontSize": 26,
                    "fontWeight": "BOLD"
                  },
                  {
                    "type": "SPACER",
                    "id": "c_welcome_spacer",
                    "modifier": {
                      "height": 32
                    }
                  },
                  {
                    "type": "IMAGE",
                    "id": "c_hero_image",
                    "url": "https://picsum.photos/400/200?random=3",
                    "contentDescription": "C 화면 히어로 이미지",
                    "modifier": {
                      "width": "match_parent",
                      "height": 200
                    }
                  },
                  {
                    "type": "SPACER",
                    "id": "c_image_spacer",
                    "modifier": {
                      "height": 24
                    }
                  },
                  {
                    "type": "TEXT",
                    "id": "c_description",
                    "text": "이 화면은 다양한 SDUI 컴포넌트들을 보여주는 데모 화면입니다. 이미지, 텍스트, 버튼 등 다양한 요소들을 확인할 수 있습니다.",
                    "fontSize": 16
                  },
                  {
                    "type": "SPACER",
                    "id": "c_desc_spacer",
                    "modifier": {
                      "height": 32
                    }
                  },
                  {
                    "type": "ROW",
                    "id": "c_action_row",
                    "modifier": {
                      "padding": 8
                    },
                    "children": [
                      {
                        "type": "BUTTON",
                        "id": "c_action_1",
                        "text": "액션 1",
                        "action": {
                          "type": "TOAST",
                          "message": "C 화면에서 액션 1이 실행되었습니다!"
                        }
                      },
                      {
                        "type": "SPACER",
                        "id": "c_action_spacer",
                        "modifier": {
                          "width": 16
                        }
                      },
                      {
                        "type": "BUTTON",
                        "id": "c_action_2",
                        "text": "액션 2",
                        "action": {
                          "type": "TOAST",
                          "message": "C 화면에서 액션 2가 실행되었습니다!"
                        }
                      }
                    ]
                  },
                  {
                    "type": "SPACER",
                    "id": "c_final_spacer",
                    "modifier": {
                      "height": 24
                    }
                  },
                  {
                    "type": "CARD",
                    "id": "c_info_card",
                    "modifier": {
                      "elevation": 6,
                      "padding": 20
                    },
                    "children": [
                      {
                        "type": "TEXT",
                        "id": "c_card_title",
                        "text": "정보 카드",
                        "fontSize": 20,
                        "fontWeight": "BOLD"
                      },
                      {
                        "type": "SPACER",
                        "id": "c_card_title_spacer",
                        "modifier": {
                          "height": 12
                        }
                      },
                      {
                        "type": "TEXT",
                        "id": "c_card_content",
                        "text": "이 카드는 다양한 정보를 표시할 수 있습니다. SDUI의 유연성을 보여주는 좋은 예시입니다.",
                        "fontSize": 14
                      }
                    ]
                  }
                ]
              }
            ]
          }
        }
        """.trimIndent()
} 
