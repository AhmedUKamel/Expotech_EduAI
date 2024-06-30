package org.ahmedukamel.eduai.controller.employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.employee.AddEmployeeRequest;
import org.ahmedukamel.eduai.dto.employee.AssignPositionToEmployeeRequest;
import org.ahmedukamel.eduai.service.employee.EmployeeManagementService;
import org.ahmedukamel.eduai.service.employee.IEmployeeManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAuthority('EMPLOYEE_MANAGER')")
@RequestMapping(value = "api/v1/employees")
public class EmployeeManagementController {
    private final IEmployeeManagementService service;

    public EmployeeManagementController(EmployeeManagementService service) {
        this.service = service;
    }

    @GetMapping(value = "unemployed")
    public ResponseEntity<?> getUnEmployedEmployees(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getUnEmployedEmployees(pageSize, pageNumber));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllEmployees(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        return ResponseEntity.ok().body(service.getAllEmployees(pageSize, pageNumber));
    }

    @PostMapping(value = "assign")
    public ResponseEntity<?> assignPositionToEmployee(
            @Valid @RequestBody AssignPositionToEmployeeRequest request) {

        return ResponseEntity.accepted().body(service.assignPositionToEmployee(request));
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody AddEmployeeRequest request) {

        return ResponseEntity.created(URI.create("api/v1/employees/new"))
                .body(service.addEmployee(request));
    }
}