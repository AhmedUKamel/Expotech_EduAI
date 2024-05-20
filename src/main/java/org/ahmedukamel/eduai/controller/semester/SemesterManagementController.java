package org.ahmedukamel.eduai.controller.semester;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.semester.SemesterRequest;
import org.ahmedukamel.eduai.service.semester.ISemesterManagementService;
import org.ahmedukamel.eduai.service.semester.SemesterManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/semester")
public class SemesterManagementController {
    private final ISemesterManagementService service;

    public SemesterManagementController(SemesterManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createSemester(@Valid @RequestBody SemesterRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/semester"))
                .body(service.createSemester(request));
    }

    @PutMapping(value = "{semesterId}")
    public ResponseEntity<?> updateSemester(@Min(value = 1) @PathVariable(value = "semesterId") Integer id,
                                            @Valid @RequestBody SemesterRequest request) {
        return ResponseEntity.accepted().body(service.updateSemester(id, request));
    }

    @DeleteMapping(value = "{semesterId}")
    public ResponseEntity<?> deleteSemester(@Min(value = 1) @PathVariable(value = "semesterId") Integer id) {
        return ResponseEntity.accepted().body(service.deleteSemester(id));
    }

    @GetMapping(value = "{semesterId}")
    public ResponseEntity<?> getSemester(@Min(value = 1) @PathVariable(value = "semesterId") Integer id) {
        return ResponseEntity.ok().body(service.getSemester(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllSemesters(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                             @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllSemesters(pageSize, pageNumber));
    }
}