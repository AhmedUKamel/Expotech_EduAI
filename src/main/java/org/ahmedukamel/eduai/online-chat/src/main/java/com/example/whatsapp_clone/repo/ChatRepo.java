package com.example.whatsapp_clone.repo;

import com.example.whatsapp_clone.entity.Chat;
import com.example.whatsapp_clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat, Integer> {
    @Query("SELECT c FROM Chat c JOIN c.userSet u WHERE u.id = :userId")
    List<Chat> findChatByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.userSet AND :reqUser MEMBER OF c.userSet")
    Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);
}
