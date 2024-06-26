package com.example.whatsapp_clone.service;

import com.example.whatsapp_clone.entity.Chat;
import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.exception.ChatException;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.repo.ChatRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{
    private final ChatRepo chatRepo;
    private final UserService userService;
    public ChatServiceImpl(ChatRepo chatRepo, UserService userService) {
        this.chatRepo = chatRepo;
        this.userService = userService;
    }
    @Override
    public Chat createChat(User reqUser, Integer userId) throws UserException {
        Optional<User> user = userService.findUserById(userId);
        Chat isChatExist = chatRepo.findSingleChatByUserIds(user.get(),reqUser);
        if(isChatExist != null){
            return isChatExist;
        }
        Chat chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.getUserSet().add(user.get());
        chat.getUserSet().add(reqUser);
        return chat;
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {
        Optional<Chat> chat = chatRepo.findById(chatId);
        if(chat != null){
            return chat.get();
        }
        throw new ChatException("Chat Not Found");
    }

    @Override
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException {
        User user = userService.findUserById(userId).orElseThrow(null);
        List<Chat> chats = chatRepo.findChatByUserId(user.getId());
        return chats;
    }

    @Override
    public void deleteChat(Integer chatId, Integer userId){
        Chat chat = chatRepo.findById(chatId).orElseThrow(null);
        if(chat != null){
            chatRepo.deleteById(chatId);
        }
    }
}



//Groups


//    @Override
//    public Chat createGroup(GroupChatRequest req, User reqUser) throws UserException {
//        Chat group = new Chat();
//        group.setChatName(req.getChatImage());
//        group.setGroup(true);
//        group.setChatImage(req.getChatName());
//        group.setCreatedBy(reqUser);
//        group.getAdminSet().add(reqUser);
//        for (Integer userId : req.getUserIds()) {
//            User user = userService.findUserById(userId).orElseThrow(null);
//            group.getUserSet().add(user);
//        }
//        return group;
//    }

//    @Override
//    public Chat addUserToGroup(Integer chatId, Integer userId , User userReq) throws UserException, ChatException {
//        Optional<Chat> optChat = chatRepo.findById(chatId);
//        User user = userService.findUserById(userId).orElseThrow(null);
//        if (optChat.isPresent()){
//            Chat chat = optChat.get();
//            if (chat.getAdminSet().contains(userReq)){
//               chat.getUserSet().add(user);
//               return chatRepo.save(chat);
//            }
//            throw new UserException("You are not an admin");
//
//        }
//        throw new ChatException("chat not found with this id" + chatId);
//    }

//    @Override
//    public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws ChatException, UserException {
//        Optional<Chat> optChat = chatRepo.findById(chatId);
//        if (optChat.isPresent()){
//            Chat chat = optChat.get();
//            if (chat.getUserSet().contains(reqUser)){
//                chat.setChatName(groupName);
//                return chatRepo.save(chat);
//            }
//            throw new ChatException("You are not member of this group");
//        }
//        throw new ChatException("chat not found with this id" + chatId);
//    }

//    @Override
//    public Chat removeUserFromGroup(Integer chatId, Integer userId, User reqUser) throws ChatException, UserException {
//        Optional<Chat> optChat = chatRepo.findById(chatId);
//        User user = userService.findUserById(userId).orElseThrow(null);
//        if (optChat.isPresent()){
//            Chat chat = optChat.get();
//            if (chat.getAdminSet().contains(reqUser)){
//                chat.getUserSet().remove(user);
//                return chatRepo.save(chat);
//            }
//            else if(chat.getUserSet().contains(reqUser)){
//                if((user.getId()) == (reqUser.getId())){
//                    chat.getUserSet().remove(user);
//                    return chatRepo.save(chat);
//                }
//            }
//            throw new UserException("You can't remove this user from group");
//        }
//        throw new ChatException("chat not found with this id" + chatId);
//    }

