package org.ahmedukamel.eduai.controller.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.ahmedukamel.eduai.dto.auth.*;
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

    @PostMapping(value = "parent-registration")
    public ResponseEntity<?> registerParent(@Valid @RequestBody ParentRegistrationRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/auth/parent-registration"))
                .body(service.registerParent(request));
    }

    @PostMapping(value = "teacher-registration")
    public ResponseEntity<?> registerTeacher(@Valid @RequestBody TeacherRegistrationRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/auth/teacher-registration"))
                .body(service.registerTeacher(request));
    }

    @PostMapping(value = "employee-registration")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeRegistrationRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/auth/employee-registration"))
                .body(service.registerEmployee(request));
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> loginUser(@NotBlank @RequestParam(value = "username") String username,
                                       @NotBlank @RequestParam(value = "password") String password) {
        return ResponseEntity.accepted().body(service.loginUser(username, password));
    }
}