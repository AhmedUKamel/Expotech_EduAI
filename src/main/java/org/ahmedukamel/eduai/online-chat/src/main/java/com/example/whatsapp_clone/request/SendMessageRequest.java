package com.example.whatsapp_clone.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendMessageRequest {
    private Integer userId;
    private Integer chatId;
    private String content;
}
