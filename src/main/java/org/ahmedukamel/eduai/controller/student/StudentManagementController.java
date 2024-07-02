package org.ahmedukamel.eduai.controller.student;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.student.AddStudentRequest;
import org.ahmedukamel.eduai.service.student.IStudentManagementService;
import org.ahmedukamel.eduai.service.student.StudentManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'STUDENT_MANAGER')")
@RequestMapping(value = "api/v1/management/student")
public class StudentManagementController {
    private final IStudentManagementService service;

    public StudentManagementController(StudentManagementService service) {
        this.service = service;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addStudent(
            @Valid @RequestBody AddStudentRequest request) {

        return ResponseEntity.created(URI.create("api/v1/management/student/new"))
                .body(service.addStudent(request));
    }

    @PutMapping(value = "account-lock/{studentId}")
    public ResponseEntity<?> setStudentAccountLock(
            @Min(value = 1) @PathVariable(value = "studentId") Long id,
            @RequestParam(value = "locked", defaultValue = "true") boolean accountLocked) {

        return ResponseEntity.accepted().body(service.setStudentAccountLock(id, accountLocked));
    }

    @GetMapping(value = "{studentId}")
    public ResponseEntity<?> getStudent(
            @Min(value = 1) @PathVariable(value = "studentId") Long id) {

        return ResponseEntity.ok().body(service.getStudent(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllStudents(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber) {

        return ResponseEntity.ok().body(service.getAllStudents(pageSize, pageNumber));
    }
}