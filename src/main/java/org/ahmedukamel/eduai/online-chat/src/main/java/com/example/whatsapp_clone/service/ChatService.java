package com.example.whatsapp_clone.service;

import com.example.whatsapp_clone.entity.Chat;
import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.exception.ChatException;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.request.GroupChatRequest;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, Integer userId) throws UserException;
    public Chat findChatById(Integer chatId) throws ChatException;
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException;
    public void deleteChat(Integer chatId, Integer userID);






















}
