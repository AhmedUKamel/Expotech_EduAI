package org.ahmedukamel.eduai.controller.bus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.bus.CreateBusRequest;
import org.ahmedukamel.eduai.dto.bus.UpdateBusRequest;
import org.ahmedukamel.eduai.service.bus.IBusManagementService;
import org.ahmedukamel.eduai.service.bus.BusManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'BUS_MANAGER')")
@RequestMapping(value = "api/v1/bus")
public class BusManagementController {
    private final IBusManagementService service;

    public BusManagementController(BusManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createBus(@Valid @RequestBody CreateBusRequest request) {
        return ResponseEntity.created(URI.create("api/v1/bus"))
                .body(service.createBus(request));
    }

    @PutMapping(value = "{busId}")
    public ResponseEntity<?> updateBus(@Min(value = 1) @PathVariable(value = "busId") Long id,
                                       @Valid @RequestBody UpdateBusRequest request) {
        return ResponseEntity.accepted().body(service.updateBus(id, request));
    }

    @DeleteMapping(value = "{busId}")
    public ResponseEntity<?> deleteBus(@Min(value = 1) @PathVariable(value = "busId") Long id) {
        return ResponseEntity.accepted().body(service.deleteBus(id));
    }

    @GetMapping(value = "{busId}")
    public ResponseEntity<?> getBus(@Min(value = 1) @PathVariable(value = "busId") Long id) {
        return ResponseEntity.ok().body(service.getBus(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllBuses(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllBuses(pageSize, pageNumber));
    }
}