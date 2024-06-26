package com.example.whatsapp_clone.controller;

import com.example.whatsapp_clone.entity.User;
import com.example.whatsapp_clone.enums.Role;
import com.example.whatsapp_clone.exception.UserException;
import com.example.whatsapp_clone.request.UpdateUserRequest;
import com.example.whatsapp_clone.response.ApiResponse;
import com.example.whatsapp_clone.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.whatsapp_clone.enums.Role.VALID_ROLES_MAP;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping("/profile")
    public ResponseEntity<User> findUserProfile(@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUser(@PathVariable("query") String query,@RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfile(jwt);
        Role reqUserRole = reqUser.getRole();

        List<User> users = userService.searchUser(query);
        Set<Role> validRoles = VALID_ROLES_MAP.get(reqUserRole);
        if (validRoles != null) {
            users = users.stream()
                    .filter(user -> validRoles.contains(user.getRole()))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UpdateUserRequest req, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserProfile(token);
        userService.updateUser(user.getId(),req);
        ApiResponse res = new ApiResponse("User updated successfully",true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
    }
}
