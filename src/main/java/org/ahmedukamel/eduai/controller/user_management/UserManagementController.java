package org.ahmedukamel.eduai.controller.user_management;

import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.NotEmpty;
import org.ahmedukamel.eduai.service.user_management.IUserManagementService;
import org.ahmedukamel.eduai.service.user_management.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/v1/user-management")
public class UserManagementController {
    private final IUserManagementService service;

    public UserManagementController(UserManagementService service) {
        this.service = service;
    }

    @PutMapping(value = "{userId}/picture")
    public ResponseEntity<?> uploadProfilePicture(
            @NotEmpty @RequestParam(value = "picture") MultipartFile picture) {
        return ResponseEntity.accepted().body(service.uploadProfilePicture(picture));
    }

    @DeleteMapping(value = "{userId}/picture")
    public ResponseEntity<?> deleteProfilePicture() {
        return ResponseEntity.accepted().body(service.removeProfilePicture());
    }
}