package org.ahmedukamel.eduai.dto.chat;

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
