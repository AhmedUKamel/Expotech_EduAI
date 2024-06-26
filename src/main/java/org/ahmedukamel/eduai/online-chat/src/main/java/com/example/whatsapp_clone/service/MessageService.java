package com.example.whatsapp_clone.service;

import com.example.whatsapp_clone.entity.Message;
import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.exception.ChatException;
import com.example.whatsapp_clone.exception.MessageException;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.request.SendMessageRequest;

import java.util.List;

public interface MessageService {
    public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
    public List<Message> getChatMessages(Integer chatId , User reqUser) throws ChatException, UserException;
    public Message findMessageById(Integer messageId) throws MessageException;
    public void deleteMessage(Integer messageId , User reqUser) throws MessageException, UserException;














}
