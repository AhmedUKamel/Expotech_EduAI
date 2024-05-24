package org.ahmedukamel.eduai.controller.position;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.position.CreatePositionRequest;
import org.ahmedukamel.eduai.dto.position.UpdatePositionRequest;
import org.ahmedukamel.eduai.service.position.IPositionManagementService;
import org.ahmedukamel.eduai.service.position.PositionManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/position")
public class PositionManagementController {
    private final IPositionManagementService service;

    public PositionManagementController(PositionManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createPosition(@Valid @RequestBody CreatePositionRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/position"))
                .body(service.createPosition(request));
    }

    @PutMapping(value = "{positionId}")
    public ResponseEntity<?> updatePosition(@Min(value = 1) @PathVariable(value = "positionId") Integer id,
                                            @Valid @RequestBody UpdatePositionRequest request) {
        return ResponseEntity.accepted().body(service.updatePosition(id, request));
    }

    @DeleteMapping(value = "{positionId}")
    public ResponseEntity<?> deletePosition(@Min(value = 1) @PathVariable(value = "positionId") Integer id) {
        return ResponseEntity.accepted().body(service.deletePosition(id));
    }

    @GetMapping(value = "{positionId}")
    public ResponseEntity<?> getPosition(@Min(value = 1) @PathVariable(value = "positionId") Integer id) {
        return ResponseEntity.ok().body(service.getPosition(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllPositions(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                             @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllPositions(pageSize, pageNumber));
    }

    @GetMapping(value = "by-department")
    public ResponseEntity<?> getPositionsByDepartment(@Min(value = 1) @RequestParam(value = "id") Integer departmentId,
                                                      @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                                      @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getPositionsByDepartment(departmentId, pageSize, pageNumber));
    }
}