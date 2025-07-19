package pl.rengreen.taskmanager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rengreen.taskmanager.dto.*;
import pl.rengreen.taskmanager.model.Notification;
import pl.rengreen.taskmanager.model.User;
import pl.rengreen.taskmanager.repository.NotificationRepository;
import pl.rengreen.taskmanager.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of NotificationService.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public NotificationResponse createNotification(NotificationRequest request) {
        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
        }
        Notification notification = new Notification();
        notification.setMessage(request.getMessage());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        notification.setDeleted(false);
        notification.setUser(user);
        Notification saved = notificationRepository.save(notification);
        return toResponse(saved);
    }

    @Override
    public Page<NotificationResponse> getNotifications(Long userId, String status, Pageable pageable) {
        Page<Notification> page = notificationRepository.findByUserIdAndDeletedFalseOrderByCreatedAtDesc(userId, pageable);
        return page.map(this::toResponse);
    }

    @Override
    @Transactional
    public void markAsRead(Long userId, List<Long> notificationIds) {
        List<Notification> notifications = notificationRepository.findByIdInAndUserIdAndDeletedFalse(notificationIds, userId);
        if (notifications.size() != notificationIds.size()) {
            throw new EntityNotFoundException("Some notifications not found or do not belong to user");
        }
        for (Notification n : notifications) {
            n.setRead(true);
        }
        notificationRepository.saveAll(notifications);
    }

    @Override
    @Transactional
    public void deleteNotifications(Long userId, List<Long> notificationIds) {
        List<Notification> notifications = notificationRepository.findByIdInAndUserIdAndDeletedFalse(notificationIds, userId);
        if (notifications.size() != notificationIds.size()) {
            throw new EntityNotFoundException("Some notifications not found or do not belong to user");
        }
        for (Notification n : notifications) {
            n.setDeleted(true);
        }
        notificationRepository.saveAll(notifications);
    }

    @Override
    public NotificationResponse getNotificationDetail(Long userId, Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .filter(n -> !n.isDeleted() && n.getUser().getId().equals(userId))
                .orElseThrow(() -> new EntityNotFoundException("Notification not found or does not belong to user"));
        return toResponse(notification);
    }

    private NotificationResponse toResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setMessage(notification.getMessage());
        response.setCreatedAt(notification.getCreatedAt());
        response.setRead(notification.isRead());
        return response;
    }
}
