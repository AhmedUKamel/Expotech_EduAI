package org.ahmedukamel.eduai.controller.event;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.annotation.NotEmpty;
import org.ahmedukamel.eduai.dto.api.FileResponse;
import org.ahmedukamel.eduai.dto.event.CreateEventRequest;
import org.ahmedukamel.eduai.dto.event.UpdateEventRequest;
import org.ahmedukamel.eduai.service.event.EventManagementService;
import org.ahmedukamel.eduai.service.event.IEventManagementService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
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

    @PutMapping(value = "{noticeId}/upload")
    public ResponseEntity<?> uploadEventFile(@Min(value = 1) @PathVariable(value = "noticeId") Long id,
                                             @NotEmpty @RequestParam(value = "pdf") MultipartFile pdf) {
        return ResponseEntity.accepted().body(service.uploadEventFile(id, pdf));
    }

    @DeleteMapping(value = "{noticeId}")
    public ResponseEntity<?> deleteEventFile(@Min(value = 1) @PathVariable(value = "noticeId") Long id) {
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
    public ResponseEntity<?> getAllEventsForSchool(@RequestParam(value = "schoolId") int schoolId,
                                                     @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                     @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllEventsForSchool(schoolId, pageSize, pageNumber));
    }

    @GetMapping(value = "public/{eventId}/file")
    public ResponseEntity<?> getNoticePdf(@Min(value = 1) @PathVariable(value = "eventId") Long id) {
        FileResponse response = service.getEventFile(id);
        return ResponseEntity.ok().headers(response.headers()).body(response.date());
    }
}