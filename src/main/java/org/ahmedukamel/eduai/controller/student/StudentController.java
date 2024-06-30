package org.ahmedukamel.eduai.controller.student;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.student.CreateStudentRequest;
import org.ahmedukamel.eduai.dto.student.UpdateStudentRequest;
import org.ahmedukamel.eduai.service.student.IStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
@PreAuthorize(value = "hasAuthority('ADMIN')")
@RequestMapping(value = "api/v1/student")
public class StudentController {
    private final IStudentService studentService;
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/student"))
                .body(studentService.createStudent(request));
    }
    @PutMapping(value = "{studentId}")
    public ResponseEntity<?> updateStudent(@Min(value = 1) @PathVariable(value = "studentId") Long id,
                                          @Valid @RequestBody UpdateStudentRequest request) {
        return ResponseEntity.accepted().body(studentService.updateStudent(id, request));
    }

    @DeleteMapping(value = "{studentId}")
    public ResponseEntity<?> deleteStudent(@Min(value = 1) @PathVariable(value = "studentId") Long id) {
        return ResponseEntity.accepted().body(studentService.deleteStudent(id));
    }

    @GetMapping(value = "{studentId}")
    public ResponseEntity<?> getStudent(@Min(value = 1) @PathVariable(value = "studentId") Long id) {
        return ResponseEntity.ok().body(studentService.getStudent(id));
    }
    @GetMapping(value = "all")
    public ResponseEntity<?> getAllStudent(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                           @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(studentService.getAllStudent(pageSize, pageNumber));
    }
}
