package org.ahmedukamel.eduai.controller.teacher;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.teacher.AddTeacherRequest;
import org.ahmedukamel.eduai.dto.teacher.UpdateTeacherRequest;
import org.ahmedukamel.eduai.service.teacher.ITeacherManagementService;
import org.ahmedukamel.eduai.service.teacher.TeacherManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'TEACHER_MANAGER')")
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

    @PutMapping(value = "account-lock/{teacherId}")
    public ResponseEntity<?> setTeacherAccountLock(
            @Min(value = 1) @PathVariable(value = "teacherId") Long id,
            @RequestParam(value = "locked", defaultValue = "true") boolean accountLocked) {

        return ResponseEntity.accepted().body(service.setTeacherAccountLock(id, accountLocked));
    }
    @PreAuthorize(value = "hasAnyAuthority('ADMIN', 'TEACHER_MANAGER','TEACHER')")
    @PutMapping(value = "{teacherId}")
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody UpdateTeacherRequest request) {
        return ResponseEntity.accepted().body(service.updateTeacher(request));
    }

    @GetMapping(value = "{teacherId}")
    public ResponseEntity<?> getTeacher(
            @Min(value = 1) @PathVariable(value = "teacherId") Long id) {

        return ResponseEntity.ok().body(service.getTeacher(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllTeachers(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "archived", defaultValue = "false") boolean archived) {

        return ResponseEntity.ok().body(service.getAllTeachers(pageSize, pageNumber, archived));
    }
}