package org.ahmedukamel.eduai.controller.course;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.course.AddCourseRequest;
import org.ahmedukamel.eduai.dto.course.UpdateCourseRequest;
import org.ahmedukamel.eduai.service.course.CourseManagementService;
import org.ahmedukamel.eduai.service.course.ICourseManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'COURSE_MANAGER')")
@RequestMapping(value = "api/v1/management/course")
public class CourseManagementController {
    private final ICourseManagementService service;

    public CourseManagementController(CourseManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> addCourse(
            @Valid @RequestBody AddCourseRequest request) {

        return ResponseEntity.created(URI.create("api/v1/management/course"))
                .body(service.addCourse(request));
    }

    @PutMapping(value = "{courseId}")
    public ResponseEntity<?> updateCourse(
            @Min(value = 1) @PathVariable(value = "courseId") Long id,
            @Valid @RequestBody UpdateCourseRequest request) {

        return ResponseEntity.accepted().body(service.updateCourse(id, request));
    }

    @DeleteMapping(value = "{courseId}")
    public ResponseEntity<?> deleteCourse(
            @Min(value = 1) @PathVariable(value = "courseId") Long id) {

        return ResponseEntity.accepted().body(service.deleteCourse(id));
    }

    @GetMapping(value = "{courseId}")
    public ResponseEntity<?> getCourse(
            @Min(value = 1) @PathVariable(value = "courseId") Long id) {

        return ResponseEntity.ok().body(service.getCourse(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllCourses(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber
    ) {

        return ResponseEntity.ok().body(service.getAllCourses(pageNumber, pageSize));
    }
}