package org.ahmedukamel.eduai.controller.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.ahmedukamel.eduai.dto.auth.StudentRegistrationRequest;
import org.ahmedukamel.eduai.service.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthController {
    private final IAuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping(value = "student-registration")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentRegistrationRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/auth/student-registration"))
                .body(service.registerStudent(request));
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> loginUser(@NotBlank @RequestParam(value = "username") String username,
                                       @NotBlank @RequestParam(value = "password") String password) {
        return ResponseEntity.accepted().body(service.loginUser(username, password));
    }
}