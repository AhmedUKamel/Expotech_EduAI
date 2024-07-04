package org.ahmedukamel.eduai.controller.training_program.attendance;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.employee_training_attendance.CreateEmployeeTrainingAttendanceRequest;
import org.ahmedukamel.eduai.dto.employee_training_attendance.UpdateEmployeeTrainingAttendanceRequest;
import org.ahmedukamel.eduai.service.employee_training_attendance.EmployeeTrainingAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'TRAINING_PROGRAM_MANAGER')")
@RequestMapping(value = "api/v1/training_program/employee_attendance")
public class EmployeeTrainingAttendanceController {
    private final EmployeeTrainingAttendanceService service;

    public EmployeeTrainingAttendanceController(EmployeeTrainingAttendanceService service) {
        this.service = service;
    }
    @PostMapping(value = "new")
    public ResponseEntity<?> addEmployeeTrainingAttendance(
            @Valid @RequestBody CreateEmployeeTrainingAttendanceRequest request) {
        return ResponseEntity.created(URI.create("api/v1/training_program/employee_attendance/new"))
                .body(service.addEmployeeTrainingAttendance(request));
    }
    @GetMapping(value = "{employeeTrainingAttendanceId}")
    public ResponseEntity<?> getEmployeeTrainingAttendance(
            @Min(value = 1) @PathVariable(value = "employeeTrainingAttendanceId") Long id) {
        return ResponseEntity.accepted().body(service.getEmployeeTrainingAttendance(id));
    }
    @PutMapping(value = "{employeeTrainingAttendanceId}")
    public ResponseEntity<?> updateEmployeeTrainingAttendance(
            @Min(value = 1) @PathVariable(value = "employeeTrainingAttendanceId") Long id,
            @Valid @RequestBody UpdateEmployeeTrainingAttendanceRequest request) {
        return ResponseEntity.accepted().body(service.updateEmployeeTrainingAttendance(id, request));
    }
    @DeleteMapping(value = "{employeeTrainingAttendanceId}")
    public ResponseEntity<?> deleteEmployeeTrainingAttendance(
            @Min(value = 1) @PathVariable(value = "employeeTrainingAttendanceId") Long id) {
        return ResponseEntity.ok().body(service.softDeleteEmployeeTrainingAttendance(id));
    }
    @GetMapping(value = "all")
    public ResponseEntity<?> getAllEmployeeTrainingAttendance(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllEmployeeTrainingAttendance(pageSize, pageNumber));
    }
}
