package org.ahmedukamel.eduai.controller.employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.employee.AddEmployeeRequest;
import org.ahmedukamel.eduai.model.enumeration.EmployeeType;
import org.ahmedukamel.eduai.service.employee.EmployeeManagementService;
import org.ahmedukamel.eduai.service.employee.IEmployeeManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'EMPLOYEE_MANAGER')")
@RequestMapping(value = "api/v1/management/employee")
public class EmployeeManagementController {
    private final IEmployeeManagementService parentService;

    public EmployeeManagementController(EmployeeManagementService service) {
        this.parentService = service;
    }

    @PostMapping(value = "new")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody AddEmployeeRequest request,
            @RequestParam(value = "type") EmployeeType employeeType) {

        return ResponseEntity.created(URI.create("api/v1/management/employee/new"))
                .body(parentService.addEmployee(request, employeeType));
    }

    @PutMapping(value = "account-lock/{employeeId}")
    public ResponseEntity<?> setEmployeeAccountLock(
            @Min(value = 1) @PathVariable(value = "employeeId") Long id,
            @RequestParam(value = "locked", defaultValue = "true") boolean accountLocked) {

        return ResponseEntity.accepted().body(parentService.setEmployeeAccountLock(id, accountLocked));
    }

    @GetMapping(value = "{employeeId}")
    public ResponseEntity<?> getEmployee(
            @Min(value = 1) @PathVariable(value = "employeeId") Long id) {

        return ResponseEntity.ok().body(parentService.getEmployee(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllEmployees(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "archived", defaultValue = "false") boolean archived,
            @RequestParam(value = "type") EmployeeType employeeType) {

        return ResponseEntity.ok().body(parentService.getAllEmployees(pageSize, pageNumber, employeeType, archived));
    }
}