package com.example.whatsapp_clone.service;

import com.example.whatsapp_clone.config.TokenProvider;
import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.repo.UserRepo;
import com.example.whatsapp_clone.request.UpdateUserRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepo repo;
    private TokenProvider tokenProvider;

    public UserServiceImpl(UserRepo repo, TokenProvider tokenProvider) {
        this.repo = repo;
        this.tokenProvider = tokenProvider;
    }


    @Override
    public User findUserProfile(String jwt) throws UserException {
        String email = tokenProvider.getEmailFromToken(jwt);
        if (email != null){
            throw  new BadCredentialsException("Received invalid token");
        }
        User user = repo.findByEmail(email);
        if(user == null){
            throw new UserException("User with id " + email + " does not exist");
        }
        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException {
        Optional<User> optionalUser = repo.findById(userId);
        User newUser = optionalUser.map(user -> {
            if (req.getFullName() != null) {
                user.setFullName(req.getFullName());
            }
            return user;
        }).orElseThrow(() -> new UserException("User with id " + userId + " does not exist"));

        return repo.save(newUser);
    }
    @Override
    public List<User> searchUser(String query) {
        return repo.searchUser(query);
    }

    @Override
    public Optional<User> findUserById(int id) throws UserException {
        Optional<User> user =repo.findById(id);
        if (user.isPresent()){
            return user;
        }
        throw new UserException("User with id " + id + " does not exist");
    }


}
