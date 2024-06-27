package org.ahmedukamel.eduai.controller.department;

import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.service.department.DepartmentService;
import org.ahmedukamel.eduai.service.department.IDepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/department/public")
public class DepartmentController {
    private final IDepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(value = "{departmentId}")
    public ResponseEntity<?> getDepartment(@Min(value = 1) @PathVariable("departmentId") Integer id) {
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