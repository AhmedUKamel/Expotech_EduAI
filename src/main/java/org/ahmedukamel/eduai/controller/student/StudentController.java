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
@PreAuthorize(value = "hasAuthority('STUDENT_MANAGER')")
@RequestMapping(value = "api/v1/management/student")
public class StudentController {
    private final IStudentManagementService studentService;

    public StudentController(StudentManagementService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addStudent(
            @Valid @RequestBody AddStudentRequest request) {

        return ResponseEntity.created(URI.create("api/v1/management/student/new"))
                .body(studentService.addStudent(request));
    }

    @DeleteMapping(value = "{studentId}")
    public ResponseEntity<?> deleteStudent(
            @Min(value = 1) @PathVariable(value = "studentId") Long id) {

        return ResponseEntity.accepted().body(studentService.deleteStudent(id));
    }

    @GetMapping(value = "{studentId}")
    public ResponseEntity<?> getStudent(
            @Min(value = 1) @PathVariable(value = "studentId") Long id) {

        return ResponseEntity.ok().body(studentService.getStudent(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllStudents(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(studentService.getAllStudents(pageSize, pageNumber));
    }
}