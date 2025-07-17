package pl.rengreen.taskmanager.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO for creating a notification.
 */
@Data
public class NotificationRequest {

    @NotNull
    private Long userId;

    private Long taskId;

    @NotNull
    @Size(min = 1, max = 255)
    private String message;

    @NotNull
    private LocalDateTime scheduledAt;

    private boolean recurring = false;

    private String recurrenceType; // e.g., DAILY, NONE
}
