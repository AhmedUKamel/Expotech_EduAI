package org.ahmedukamel.eduai.controller.notification;

import jakarta.validation.Valid;
import org.ahmedukamel.eduai.dto.notification.CreateNotificationRequest;
import org.ahmedukamel.eduai.service.notification.INotificationManagementService;
import org.ahmedukamel.eduai.service.notification.NotificationManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v1/notification")
public class NotificationManagementController {
    private final INotificationManagementService service;

    public NotificationManagementController(NotificationManagementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@Valid @RequestBody CreateNotificationRequest request) {
        return ResponseEntity.created(URI.create("api/v1/notification"))
                .body(service.createNotification(request));
    }
}