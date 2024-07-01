package org.ahmedukamel.eduai.controller.auth;

import jakarta.validation.Valid;
import org.ahmedukamel.eduai.dto.auth.LoginRequest;
import org.ahmedukamel.eduai.dto.employee.EmployeeRegistrationRequest;
import org.ahmedukamel.eduai.dto.parent.ParentRegistrationRequest;
import org.ahmedukamel.eduai.dto.student.StudentRegistrationRequest;
import org.ahmedukamel.eduai.dto.teacher.TeacherRegistrationRequest;
import org.ahmedukamel.eduai.service.auth.AuthService;
import org.ahmedukamel.eduai.service.auth.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthController {
    private final IAuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping(value = "student-registration")
    public ResponseEntity<?> registerStudent(
            @Valid @RequestBody StudentRegistrationRequest request) {

        return ResponseEntity.created(URI.create("/api/v1/auth/student-registration"))
                .body(service.registerStudent(request));
    }

    @PostMapping(value = "parent-registration")
    public ResponseEntity<?> registerParent(
            @Valid @RequestBody ParentRegistrationRequest request) {

        return ResponseEntity.created(URI.create("/api/v1/auth/parent-registration"))
                .body(service.registerParent(request));
    }

    @PostMapping(value = "teacher-registration")
    public ResponseEntity<?> registerTeacher(
            @Valid @RequestBody TeacherRegistrationRequest request) {

        return ResponseEntity.created(URI.create("/api/v1/auth/teacher-registration"))
                .body(service.registerTeacher(request));
    }

    @PostMapping(value = "employee-registration")
    public ResponseEntity<?> registerEmployee(
            @Valid @RequestBody EmployeeRegistrationRequest request) {

        return ResponseEntity.created(URI.create("/api/v1/auth/employee-registration"))
                .body(service.registerEmployee(request));
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.accepted().body(service.loginUser(request));
    }
}