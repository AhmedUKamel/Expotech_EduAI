package org.ahmedukamel.eduai.controller.room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.room.CreateRoomRequest;
import org.ahmedukamel.eduai.dto.room.UpdateRoomRequest;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.service.room.IRoomManagementService;
import org.ahmedukamel.eduai.service.room.RoomManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/room")
public class RoomManagementController {
    private final IRoomManagementService service;

    public RoomManagementController(RoomManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@Valid @RequestBody CreateRoomRequest request) {
        return ResponseEntity.created(URI.create("api/v1/room"))
                .body(service.createRoom(request));
    }

    @PutMapping(value = "{roomId}")
    public ResponseEntity<?> updateRoom(@Min(value = 1) @PathVariable(value = "roomId") Long id,
                                        @Valid @RequestBody UpdateRoomRequest request) {
        return ResponseEntity.accepted().body(service.updateRoom(id, request));
    }

    @DeleteMapping(value = "{roomId}")
    public ResponseEntity<?> deleteRoom(@Min(value = 1) @PathVariable(value = "roomId") Long id) {
        return ResponseEntity.accepted().body(service.deleteRoom(id));
    }

    @GetMapping(value = "{roomId}")
    public ResponseEntity<?> getRoom(@Min(value = 1) @PathVariable(value = "roomId") Long id) {
        return ResponseEntity.ok().body(service.getRoom(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllRoom(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                        @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllRooms(pageSize, pageNumber));
    }

    @GetMapping(value = "by-category")
    public ResponseEntity<?> getAllRoomsByCategory(@RequestParam(value = "category") RoomCategory category,
                                                   @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") long pageSize,
                                                   @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") long pageNumber) {
        return ResponseEntity.ok().body(service.getAllRoomsByCategory(category, pageSize, pageNumber));
    }
}