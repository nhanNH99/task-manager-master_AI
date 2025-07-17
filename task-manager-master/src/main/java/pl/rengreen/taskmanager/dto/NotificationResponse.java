package pl.rengreen.taskmanager.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for returning notification data.
 */
@Data
public class NotificationResponse {

    private Long id;
    private Long userId;
    private Long taskId;
    private String message;
    private LocalDateTime scheduledAt;
    private boolean recurring;
    private String recurrenceType;
    private boolean read;
}
