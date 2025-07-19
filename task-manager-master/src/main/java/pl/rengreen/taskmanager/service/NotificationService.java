package pl.rengreen.taskmanager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.rengreen.taskmanager.dto.*;

import java.util.List;

/**
 * Service interface for notification operations.
 */
public interface NotificationService {

    NotificationResponse createNotification(NotificationRequest request);

    Page<NotificationResponse> getNotifications(Long userId, String status, Pageable pageable);

    void markAsRead(Long userId, List<Long> notificationIds);

    void deleteNotifications(Long userId, List<Long> notificationIds);

    NotificationResponse getNotificationDetail(Long userId, Long notificationId);
}
