package org.ahmedukamel.eduai.controller.notification;

import jakarta.validation.constraints.Min;
import org.ahmedukamel.eduai.service.notification.IUserNotificationService;
import org.ahmedukamel.eduai.service.notification.UserNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/my-notification")
public class UserNotificationController {
    private final IUserNotificationService service;

    public UserNotificationController(UserNotificationService service) {
        this.service = service;
    }

    @PostMapping(value = "{notificationId}/read")
    public ResponseEntity<?> readNotification(@Min(value = 1) @PathVariable(value = "notificationId") Long id) {
        return ResponseEntity.ok().body(service.readNotification(id));
    }

    @GetMapping(value = "{notificationId}")
    public ResponseEntity<?> getNotification(@Min(value = 1) @PathVariable(value = "notificationId") Long id) {
        return ResponseEntity.ok().body(service.getNotification(id));
    }

    @GetMapping(value = "all")
    public ResponseEntity<?> getAllNotifications(
            @Min(value = 1) @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @Min(value = 1) @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        return ResponseEntity.ok().body(service.getAllNotifications(pageSize, pageNumber));
    }
}