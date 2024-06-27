package org.ahmedukamel.eduai.controller.room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.room.classroom.CreateClassroomRequest;
import org.ahmedukamel.eduai.dto.room.classroom.UpdateClassroomRequest;
import org.ahmedukamel.eduai.service.room.classroom.ClassroomManagementService;
import org.ahmedukamel.eduai.service.room.classroom.IClassroomManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/classroom")
public class ClassroomManagementController {
    private final IClassroomManagementService service;

    public ClassroomManagementController(ClassroomManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createClassroom(@Valid @RequestBody CreateClassroomRequest request) {
        return ResponseEntity.created(URI.create("api/v1/classroom"))
                .body(service.createClassroom(request));
    }

    @PutMapping(value = "{classroomId}")
    public ResponseEntity<?> updateClassroom(@Min(value = 1) @PathVariable(value = "classroomId") Long id,
                                             @Valid @RequestBody UpdateClassroomRequest request) {
        return ResponseEntity.accepted().body(service.updateClassroom(id, request));
    }

    @DeleteMapping(value = "{classroomId}")
    public ResponseEntity<?> deleteClassroom(@Min(value = 1) @PathVariable(value = "classroomId") Long id) {
        return ResponseEntity.accepted().body(service.deleteClassroom(id));
    }

    @GetMapping(value = "{classroomId}")
    public ResponseEntity<?> getClassroom(@Min(value = 1) @PathVariable(value = "classroomId") Long id) {
        return ResponseEntity.ok().body(service.getClassroom(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllClassroom(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                             @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllClassrooms(pageSize, pageNumber));
    }
}