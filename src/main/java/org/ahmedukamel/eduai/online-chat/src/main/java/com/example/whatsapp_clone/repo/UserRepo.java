package com.example.whatsapp_clone.repo;

import com.example.whatsapp_clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    @Query("select u from User u where u.username like %:query% or u.email like %:query%")
    List<User>searchUser(@Param("query") String query);
}
