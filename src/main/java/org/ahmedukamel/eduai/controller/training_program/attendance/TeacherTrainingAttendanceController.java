package org.ahmedukamel.eduai.controller.training_program.attendance;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.teacher_training_attendance.CreateTeacherTrainingAttendanceRequest;
import org.ahmedukamel.eduai.dto.teacher_training_attendance.UpdateTeacherTrainingAttendanceRequest;
import org.ahmedukamel.eduai.service.teacher_training_attendance.TeacherTrainingAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'TRAINING_PROGRAM_MANAGER')")
@RequestMapping(value = "api/v1/training_program/teacher_attendance")
public class TeacherTrainingAttendanceController {
    private final TeacherTrainingAttendanceService  service;

    public TeacherTrainingAttendanceController(TeacherTrainingAttendanceService service) {
        this.service = service;
    }
    @PostMapping(value = "new")
    public ResponseEntity<?> addTeacherTrainingAttendance(
            @Valid @RequestBody CreateTeacherTrainingAttendanceRequest request) {
        return ResponseEntity.created(URI.create("api/v1/training_program/teacher_attendance/new"))
                .body(service.addTeacherTrainingAttendance(request));
    }
    @GetMapping(value = "{teacherTrainingAttendanceId}")
    public ResponseEntity<?> getTeacherTrainingAttendance(
            @Min(value = 1) @PathVariable(value = "teacherTrainingAttendanceId") Long id) {
        return ResponseEntity.ok().body(service.getTeacherTrainingAttendance(id));
    }
    @PutMapping(value = "{teacherTrainingAttendanceId}")
    public ResponseEntity<?> updateTeacherTrainingAttendance(
            @Min(value = 1) @PathVariable(value = "teacherTrainingAttendanceId") Long id,
            @Valid @RequestBody UpdateTeacherTrainingAttendanceRequest request) {
        return ResponseEntity.accepted().body(service.updateTeacherTrainingAttendance(id, request));
    }

    @DeleteMapping(value = "{teacherTrainingAttendanceId}")
    public ResponseEntity<?> deleteTeacherTrainingAttendance(
            @Min(value = 1) @PathVariable(value = "teacherTrainingAttendanceId") Long id) {
        return ResponseEntity.accepted().body(service.deleteTeacherTrainingAttendance(id));
    }
    @GetMapping(value = "all")
    public ResponseEntity<?> getAllTeacherTrainingAttendance(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllTeacherTrainingAttendance(pageSize, pageNumber));
    }
}
