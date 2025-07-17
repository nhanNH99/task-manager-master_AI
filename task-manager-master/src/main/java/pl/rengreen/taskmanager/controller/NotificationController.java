package pl.rengreen.taskmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.rengreen.taskmanager.dto.NotificationRequest;
import pl.rengreen.taskmanager.dto.NotificationResponse;
import pl.rengreen.taskmanager.service.NotificationService;

import javax.validation.Valid;

/**
 * REST controller for notifications.
 */
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Validated
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Creates a new notification.
     */
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(
            @Valid @RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.createNotification(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Gets a notification by ID for a user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotification(
            @PathVariable Long id,
            @RequestParam Long userId) {
        NotificationResponse response = notificationService.getNotification(id, userId);
        return ResponseEntity.ok(response);
    }

    /**
     * Gets paginated notifications for a user.
     */
    @GetMapping
    public ResponseEntity<Page<NotificationResponse>> getNotificationsForUser(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<NotificationResponse> notifications = notificationService.getNotificationsForUser(
                userId, PageRequest.of(page, size));
        return ResponseEntity.ok(notifications);
    }

    /**
     * Marks a notification as read.
     */
    @PostMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long id,
            @RequestParam Long userId) {
        notificationService.markAsRead(id, userId);
        return ResponseEntity.ok().build();
    }
}
