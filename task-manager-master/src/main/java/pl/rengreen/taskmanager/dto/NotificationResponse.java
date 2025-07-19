package pl.rengreen.taskmanager.dto;

import java.time.LocalDateTime;

/**
 * DTO for notification response.
 */
public class NotificationResponse {

    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private boolean read;

    public NotificationResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
}
