package com.example.whatsapp_clone.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatRequest {
    private List<Integer> userIds;
    private String chatName;
    private String chatImage;
}
