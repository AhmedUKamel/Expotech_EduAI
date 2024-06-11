package org.ahmedukamel.eduai.controller.user_management;

import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.service.user_management.AdminParentService;
import org.ahmedukamel.eduai.service.user_management.IAdminParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/administration/user-management/parent")
public class AdminParentController {
    private final IAdminParentService service;

    public AdminParentController(AdminParentService service) {
        this.service = service;
    }

    @GetMapping(value = "{parentId}")
    public ResponseEntity<?> getParent(@Min(value = 1) @PathVariable(value = "parentId") Long parentId) {
        return ResponseEntity.ok().body(service.getParent(parentId));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllParents(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllParents(pageSize, pageNumber));
    }
}