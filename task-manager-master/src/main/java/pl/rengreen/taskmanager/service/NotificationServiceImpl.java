package pl.rengreen.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rengreen.taskmanager.dto.NotificationRequest;
import pl.rengreen.taskmanager.dto.NotificationResponse;
import pl.rengreen.taskmanager.model.Notification;
import pl.rengreen.taskmanager.model.Task;
import pl.rengreen.taskmanager.model.User;
import pl.rengreen.taskmanager.repository.NotificationRepository;
import pl.rengreen.taskmanager.repository.TaskRepository;
import pl.rengreen.taskmanager.repository.UserRepository;
import pl.rengreen.taskmanager.service.NotificationService;

import javax.persistence.EntityNotFoundException;

/**
 * Implementation of NotificationService.
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Task task = null;
        if (request.getTaskId() != null) {
            task = taskRepository.findById(request.getTaskId())
                    .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        }
        Notification notification = Notification.builder()
                .user(user)
                .task(task)
                .message(request.getMessage())
                .scheduledAt(request.getScheduledAt())
                .recurring(request.isRecurring())
                .recurrenceType(request.getRecurrenceType())
                .read(false)
                .build();
        notification = notificationRepository.save(notification);
        return toResponse(notification);
    }

    @Override
    public NotificationResponse getNotification(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Notification notification = notificationRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));
        return toResponse(notification);
    }

    @Override
    public Page<NotificationResponse> getNotificationsForUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return notificationRepository.findByUserOrderByScheduledAtDesc(user, pageable)
                .map(this::toResponse);
    }

    @Override
    public void markAsRead(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Notification notification = notificationRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    private NotificationResponse toResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setUserId(notification.getUser().getId());
        response.setTaskId(notification.getTask() != null ? notification.getTask().getId() : null);
        response.setMessage(notification.getMessage());
        response.setScheduledAt(notification.getScheduledAt());
        response.setRecurring(notification.isRecurring());
        response.setRecurrenceType(notification.getRecurrenceType());
        response.setRead(notification.isRead());
        return response;
    }
}
