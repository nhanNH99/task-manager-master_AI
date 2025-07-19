package pl.rengreen.taskmanager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO for creating a notification.
 */
public class NotificationRequest {

    @NotBlank
    @Size(max = 500)
    private String message;

    private Long userId;

    public NotificationRequest() {}

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
