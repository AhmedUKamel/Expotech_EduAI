package org.ahmedukamel.eduai.controller.attendance;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.attendance.RecordAttendanceRequest;
import org.ahmedukamel.eduai.service.attendance.AttendanceManagementService;
import org.ahmedukamel.eduai.service.attendance.IAttendanceManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/attendance")
public class AttendanceManagementController {
    private final IAttendanceManagementService service;

    public AttendanceManagementController(AttendanceManagementService service) {
        this.service = service;
    }

    @PostMapping(value = "record")
    public ResponseEntity<?> recordAttendance(@Valid @RequestBody RecordAttendanceRequest request) {
        return ResponseEntity.created(URI.create("api/attendance/record"))
                .body(service.recordAttendance(request));
    }

    @GetMapping(value = "{attendanceId}")
    public ResponseEntity<?> getAttendance(@Min(value = 1) @PathVariable(value = "attendanceId") Long id) {
        return ResponseEntity.ok().body(service.getAttendance(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllAttendances(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getAllAttendances(pageSize, pageNumber));
    }

    @GetMapping(value = "by-student")
    public ResponseEntity<?> getAttendancesByStudent(
            @Min(value = 1) @RequestParam(value = "studentId") Long id,
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getAttendancesByStudent(id, pageSize, pageNumber));
    }

    @GetMapping(value = "by-section")
    public ResponseEntity<?> getAttendancesBySection(
            @Min(value = 1) @RequestParam(value = "sectionId") Integer id,
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getAttendancesBySection(id, pageSize, pageNumber));
    }
}