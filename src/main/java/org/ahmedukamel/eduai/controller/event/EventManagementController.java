package org.ahmedukamel.eduai.controller.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.NotEmpty;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.dto.event.UpdateEventRequest;
import org.ahmedukamel.eduai.service.event.EventManagementService;
import org.ahmedukamel.eduai.service.event.IEventManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'EVENT_MANAGER')")
@RequestMapping(value = "api/v1/event")
public class EventManagementController {
    private final IEventManagementService service;

    public EventManagementController(EventManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestParam CreateEventRequest request,
                                         @RequestParam MultipartFile file) {
        return ResponseEntity.created(URI.create("/api/v1/event"))
                .body(service.createEvent(request, file));
    }

    @PutMapping(value = "{eventId}")
    public ResponseEntity<?> updateEvent(@Min(value = 1) @PathVariable(value = "eventId") Long id,
                                                   @Valid @RequestBody UpdateEventRequest request) {
        return ResponseEntity.accepted().body(service.updateEvent(id, request));
    }

    @PutMapping(value ="add-attendee")
    public ResponseEntity<?> addAttendeeToEvent(@Min(value = 1) @RequestParam(value = "eventId") Long eventId,
                                                @Min(value = 1) @RequestParam(value = "attendeeId") Long attendeeId) {
        return ResponseEntity.accepted().body(service.addAttendeeToEvent(eventId, attendeeId));
    }

    @PutMapping(value ="remove-attendee")
    public ResponseEntity<?> removeAttendeeFromEvent(@Min(value = 1) @RequestParam(value = "eventId") Long eventId,
                                                @Min(value = 1) @RequestParam(value = "attendeeId") Long attendeeId) {
        return ResponseEntity.accepted().body(service.removeAttendeeFromEvent(eventId, attendeeId));
    }

    @PutMapping(value = "{eventId}/upload")
    public ResponseEntity<?> uploadEventFile(@Min(value = 1) @PathVariable(value = "eventId") Long id,
                                             @NotEmpty @RequestParam(value = "file") MultipartFile file) {
        return ResponseEntity.accepted().body(service.uploadEventFile(id, file));
    }

    @DeleteMapping(value = "file/{eventId}")
    public ResponseEntity<?> deleteEventFile(@Min(value = 1) @PathVariable(value = "eventId") Long id) {
        return ResponseEntity.accepted().body(service.deleteEventFile(id));
    }

    @DeleteMapping(value = "{eventId}")
    public ResponseEntity<?> deleteEvent(@Min(value = 1) @PathVariable(value = "eventId") Long id) {
        return ResponseEntity.accepted().body(service.deleteEvent(id));
    }

    @GetMapping(value = "{eventId}")
    public ResponseEntity<?> getEvent(@Min(value = 1) @PathVariable(value = "eventId") Long id) {
        return ResponseEntity.ok().body(service.getEvent(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllEventsForSchool(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                   @Min(value = 0) @RequestParam(value = "page", defaultValue = "0") int pageNumber,
                                                   @RequestParam(value = "getActive", defaultValue = "1") boolean getActive) {
        return ResponseEntity.ok().body(service.getAllEventsForSchool(getActive, pageSize, pageNumber));
    }

    @GetMapping(value = "public/{eventId}/file")
    public ResponseEntity<?> getEventFile(@Min(value = 1) @PathVariable(value = "eventId") Long id) {
        FileResponse response = service.getEventFile(id);
        return ResponseEntity.ok().headers(response.headers()).body(response.date());
    }
}