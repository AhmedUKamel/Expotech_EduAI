package com.example.whatsapp_clone.controller;

import com.example.whatsapp_clone.entity.Chat;
import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.enums.Role;
import com.example.whatsapp_clone.exception.ChatException;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.request.SingleChatRequest;
import com.example.whatsapp_clone.response.ApiResponse;
import com.example.whatsapp_clone.service.ChatService;
import com.example.whatsapp_clone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.whatsapp_clone.enums.Role.*;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;


    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/createChat")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest req, @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        User targetUser = userService.findUserById(req.getUserId()).orElseThrow(() -> new UserException("Target user not found"));

        if (isValidRoleForUser(reqUser.getRole(), targetUser.getRole())) {
            Chat chat = chatService.createChat(reqUser, req.getUserId());
            return new ResponseEntity<>(chat, HttpStatus.OK);
        }
        throw new UserException("You are not allowed to create chat with this user");
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatById(@PathVariable("chatId") Integer chatId) throws ChatException {

        Chat chat = chatService.findChatById(chatId);

        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllChatByUserIdHandler(@RequestHeader("Authorization") String jwt)throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());

        return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
    }

    @PutMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserProfile(jwt);
        chatService.deleteChat(chatId , reqUser.getId());
        ApiResponse res = new ApiResponse("Chat Deleted" , false);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

//    @PostMapping("/group")
//    public ResponseEntity<Chat> createGroupHandler(@RequestBody GroupChatRequest req, @RequestHeader("Authorization") String jwt)throws UserException {
//        User reqUser = userService.findUserProfile(jwt);
//        Chat chat = chatService.createGroup(req,reqUser);
//
//        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
//    }



//    @PutMapping("/{chatId}/add/{userId}")
//    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable Integer chatId,@PathVariable Integer userId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
//        User reqUser = userService.findUserProfile(jwt);
//
//        Chat chat = chatService.addUserToGroup(userId,chatId,reqUser);
//
//        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
//    }
//    @PutMapping("/{chatId}/remove/{userId}")
//    public ResponseEntity<Chat> removeUserFromGroupHandler(@PathVariable Integer chatId,@PathVariable Integer userId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
//        User reqUser = userService.findUserProfile(jwt);
//
//        Chat chat = chatService.removeUserFromGroup(userId,chatId,reqUser);
//
//        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
//    }
