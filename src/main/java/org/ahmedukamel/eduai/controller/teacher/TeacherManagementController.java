package org.ahmedukamel.eduai.controller.teacher;

import jakarta.validation.Valid;
import org.ahmedukamel.eduai.dto.teacher.AddTeacherRequest;
import org.ahmedukamel.eduai.service.teacher.ITeacherManagementService;
import org.ahmedukamel.eduai.service.teacher.TeacherManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAuthority('TEACHER_MANAGER')")
@RequestMapping(value = "api/v1/management/teacher")
public class TeacherManagementController {
    private final ITeacherManagementService service;

    public TeacherManagementController(TeacherManagementService service) {
        this.service = service;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addTeacher(
            @Valid @RequestBody AddTeacherRequest request) {

        return ResponseEntity.created(URI.create("api/v1/management/teacher/new"))
                .body(service.addTeacher(request));
    }
}