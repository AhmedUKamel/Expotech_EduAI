package org.ahmedukamel.eduai.controller.schedule;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.schedule.CreateScheduleItemRequest;
import org.ahmedukamel.eduai.dto.schedule.UpdateScheduleItemRequest;
import org.ahmedukamel.eduai.service.schedule.IScheduleManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/schedule")
public class ScheduleManagementController {
    
    private final IScheduleManagementService service;

    public ScheduleManagementController(IScheduleManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createScheduleItem(@Valid @RequestBody CreateScheduleItemRequest request) {
        return ResponseEntity.created(URI.create("/api/v1/schedule"))
                .body(service.createScheduleItem(request));
    }

    @PutMapping(value = "{scheduleItemId}")
    public ResponseEntity<?> updateScheduleItem(@Min(value = 1) @PathVariable(value = "scheduleItemId") Long id,
                                          @Valid @RequestBody UpdateScheduleItemRequest request) {
        return ResponseEntity.accepted().body(service.updateScheduleItem(id, request));
    }

    @DeleteMapping(value = "{scheduleItemId}")
    public ResponseEntity<?> deleteScheduleItem(@Min(value = 1) @PathVariable(value = "scheduleItemId") Long id) {
        return ResponseEntity.accepted().body(service.deleteScheduleItem(id));
    }

    @GetMapping(value = "{scheduleItemId}")
    public ResponseEntity<?> getScheduleItem(@Min(value = 1) @PathVariable(value = "scheduleItemId") Long id) {
        return ResponseEntity.ok().body(service.getScheduleItem(id));
    }

    @GetMapping(value = "teacher")
    public ResponseEntity<?> getScheduleForTeacher(@Min(value = 1) @RequestParam long teacherId,
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "getActive", defaultValue = "1") boolean getActive) {
        return ResponseEntity.ok().body(service.getScheduleForTeacher(teacherId, getActive, pageSize, pageNumber));
    }

    @GetMapping(value = "classroom")
    public ResponseEntity<?> getScheduleForClassroom(@Min(value = 1) @RequestParam long classroomId,
                                                   @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                   @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                   @RequestParam(value = "getActive", defaultValue = "1") boolean getActive) {
        return ResponseEntity.ok().body(service.getScheduleForClassroom(classroomId, getActive, pageSize, pageNumber));
    }
}
