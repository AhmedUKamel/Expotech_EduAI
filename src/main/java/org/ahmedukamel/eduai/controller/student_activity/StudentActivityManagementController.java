package org.ahmedukamel.eduai.controller.student_activity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.student_activity.CreateStudentActivityRequest;
import org.ahmedukamel.eduai.dto.student_activity.UpdateStudentActivityRequest;
import org.ahmedukamel.eduai.service.student_activity.IStudentActivityManagementService;
import org.ahmedukamel.eduai.service.student_activity.StudentActivityManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/student-activity")
public class StudentActivityManagementController {
    private final IStudentActivityManagementService service;

    public StudentActivityManagementController(StudentActivityManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createStudentActivity(@Valid @RequestBody CreateStudentActivityRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/student-activity"))
                .body(service.createStudentActivity(request));
    }

    @PutMapping(value = "{studentActivityId}")
    public ResponseEntity<?> updateStudentActivity(@Min(value = 1) @PathVariable(value = "studentActivityId") Long id,
                                                   @Valid @RequestBody UpdateStudentActivityRequest request) {
        return ResponseEntity.accepted().body(service.updateStudentActivity(id, request));
    }

    @DeleteMapping(value = "{studentActivityId}")
    public ResponseEntity<?> deleteStudentActivity(@Min(value = 1) @PathVariable(value = "studentActivityId") Long id) {
        return ResponseEntity.accepted().body(service.deleteStudentActivity(id));
    }

    @GetMapping(value = "{studentActivityId}")
    public ResponseEntity<?> getStudentActivity(@Min(value = 1) @PathVariable(value = "studentActivityId") Long id) {
        return ResponseEntity.ok().body(service.getStudentActivity(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllStudentActivities(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                     @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllStudentActivities(pageSize, pageNumber));
    }
}