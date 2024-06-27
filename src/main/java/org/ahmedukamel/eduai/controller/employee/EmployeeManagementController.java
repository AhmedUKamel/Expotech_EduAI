package org.ahmedukamel.eduai.controller.employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.employee.AssignPositionToEmployeeRequest;
import org.ahmedukamel.eduai.service.employee.EmployeeManagementService;
import org.ahmedukamel.eduai.service.employee.IEmployeeManagementService;
import org.ahmedukamel.eduai.service.exam.ExamManagementService;
import org.ahmedukamel.eduai.service.exam.IExamManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/employees")
public class EmployeeManagementController {
    private final IEmployeeManagementService service;

    public EmployeeManagementController(EmployeeManagementService service) {
        this.service = service;
    }

    @GetMapping(value = "unemployed")
    public ResponseEntity<?> getUnEmployedEmployees(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                                    @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getUnEmployedEmployees(pageSize, pageNumber));
    }

    @PostMapping(value = "assign")
    public ResponseEntity<?> assignPositionToEmployee(@Valid @RequestBody AssignPositionToEmployeeRequest request) {
        return ResponseEntity.accepted().body(service.assignPositionToEmployee(request));
    }
}