package com.example.whatsapp_clone.service;

import com.example.whatsapp_clone.entity.Chat;
import com.example.whatsapp_clone.entity.Message;
import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.exception.ChatException;
import com.example.whatsapp_clone.exception.MessageException;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.repo.MessageRepo;
import com.example.whatsapp_clone.request.SendMessageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepo messageRepo;
    private final ChatService chatService;
    private final UserService userService;

    public MessageServiceImpl(MessageRepo messageRepo, ChatService chatService, UserService userService) {
        this.messageRepo = messageRepo;
        this.chatService = chatService;
        this.userService = userService;
    }

    @Override
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException {
        User user = userService.findUserById(req.getUserId()).orElseThrow(null);
        Chat chat = chatService.findChatById(req.getChatId());
        Message message = new Message();

        message.setContent(req.getContent());
        message.setChat(chat);
        message.setUser(user);
        message.setLocalDateTime(LocalDateTime.now());
        return messageRepo.save(message);

    }

    @Override
    public List<Message> getChatMessages(Integer chatId , User reqUser) throws ChatException, UserException {
        Chat chat = chatService.findChatById(chatId);
        if(!chat.getUserSet().contains(reqUser)){
            throw  new UserException("you are not related to this chat");
        }
        List<Message> messages = messageRepo.findByChatId(chat.getId());
        return messages;
    }

    @Override
    public Message findMessageById(Integer messageId) throws MessageException {
        Message message = messageRepo.findById(messageId).orElseThrow(null);
        if (message != null) {
            return message;
        }
        throw  new MessageException("Message Not Found with id" + messageId);
    }

    @Override
    public void deleteMessage(Integer messageId , User reqUser) throws MessageException, UserException {
        Message message = findMessageById(messageId);
        if(message.getUser().getId() == reqUser.getId()){
            messageRepo.deleteById(messageId);
        }
        throw  new UserException("you can not delete this user's message" + reqUser.getFullName());
    }














}
