# SDUI-android-25
Server Driven UI 학습을 위한 레포지토리입니다.

# 예시 JSON
```json
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
```