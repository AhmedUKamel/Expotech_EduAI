package com.example.whatsapp_clone.controller;

import com.example.whatsapp_clone.entity.Message;
import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.exception.ChatException;
import com.example.whatsapp_clone.exception.MessageException;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.request.SendMessageRequest;
import com.example.whatsapp_clone.response.ApiResponse;
import com.example.whatsapp_clone.service.MessageService;
import com.example.whatsapp_clone.service.UserService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;


    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
    @PostMapping("/sendMessage")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest req, @RequestHeader("Authorization") String jwt) throws ChatException, UserException {
        User user = userService.findUserProfile(jwt);
        req.setUserId(user.getId());
        Message message = messageService.sendMessage(req);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatsMessagesHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws ChatException, UserException {
        User user = userService.findUserProfile(jwt);
        List<Message> messages = messageService.getChatMessages(chatId, user);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessageHandler(@PathVariable Integer messageId, @RequestHeader("Authorization") String jwt) throws UserException, MessageException {
        User user = userService.findUserProfile(jwt);
        messageService.deleteMessage(messageId, user);
        ApiResponse res = new ApiResponse("Message deleted successfully", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
