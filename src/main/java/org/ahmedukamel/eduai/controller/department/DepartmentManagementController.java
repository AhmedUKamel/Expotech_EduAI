package org.ahmedukamel.eduai.controller.department;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.department.CreateDepartmentRequest;
import org.ahmedukamel.eduai.dto.department.UpdateDepartmentRequest;
import org.ahmedukamel.eduai.service.department.DepartmentManagementService;
import org.ahmedukamel.eduai.service.department.IDepartmentManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/department")
public class DepartmentManagementController {
    private final IDepartmentManagementService service;

    public DepartmentManagementController(DepartmentManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody CreateDepartmentRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/department"))
                .body(service.createDepartment(request));
    }

    @PutMapping(value = "{departmentId}")
    public ResponseEntity<?> updateDepartment(@Min(value = 1) @PathVariable(value = "departmentId") Integer id,
                                              @Valid @RequestBody UpdateDepartmentRequest request) {
        return ResponseEntity.accepted().body(service.updateDepartment(id, request));
    }

    @DeleteMapping(value = "{departmentId}")
    public ResponseEntity<?> deleteDepartment(@Min(value = 1) @PathVariable(value = "departmentId") Integer id) {
        return ResponseEntity.accepted().body(service.deleteDepartment(id));
    }

    @GetMapping(value = "{departmentId}")
    public ResponseEntity<?> getDepartment(@PathVariable(value = "departmentId") Integer id) {
        return ResponseEntity.ok().body(service.getDepartment(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllDepartments(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                               @Min(value = 1) @RequestParam(value = "number", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllDepartments(pageSize, pageNumber));
    }

    @GetMapping(value = "by-school")
    public ResponseEntity<?> getSchoolDepartments(@Min(value = 1) @RequestParam(value = "id") Integer schoolId,
                                                  @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                                  @Min(value = 1) @RequestParam(value = "number", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getSchoolDepartments(schoolId, pageSize, pageNumber));
    }
}