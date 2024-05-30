package org.ahmedukamel.eduai.controller.interaction;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.dto.interaction.CreateInteractionRequest;
import org.ahmedukamel.eduai.service.interaction.IInteractionService;
import org.ahmedukamel.eduai.service.interaction.InteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/my-interaction")
public class InteractionController {
    private final IInteractionService service;

    public InteractionController(InteractionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createInteraction(@Valid @RequestBody CreateInteractionRequest request) {
        return ResponseEntity.created(URI.create("api/v1/my-interaction"))
                .body(service.createInteraction(request));
    }

    @GetMapping(value = "my")
    public ResponseEntity<?> getMyInteractions(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                               @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getMyInteractions(pageSize, pageNumber));
    }

    @GetMapping(value = "tome")
    public ResponseEntity<?> getInteractionsToMe(@Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
                                                 @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getInteractionsToMe(pageSize, pageNumber));
    }
}