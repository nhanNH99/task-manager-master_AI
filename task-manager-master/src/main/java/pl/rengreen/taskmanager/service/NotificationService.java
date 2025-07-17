package pl.rengreen.taskmanager.service;

import pl.rengreen.taskmanager.dto.NotificationRequest;
import pl.rengreen.taskmanager.dto.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for notification business logic.
 */
public interface NotificationService {

    /**
     * Creates a new notification.
     */
    NotificationResponse createNotification(NotificationRequest request);

    /**
     * Gets a notification by ID for a user.
     */
    NotificationResponse getNotification(Long id, Long userId);

    /**
     * Gets paginated notifications for a user.
     */
    Page<NotificationResponse> getNotificationsForUser(Long userId, Pageable pageable);

    /**
     * Marks a notification as read.
     */
    void markAsRead(Long id, Long userId);
}
