package com.example.whatsapp_clone.service;

import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.request.UpdateUserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> searchUser(String query);

    public Optional<User> findUserById(int id) throws UserException;

    public User findUserProfile(String jwt) throws UserException;

    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException;
}
