package pl.rengreen.taskmanager.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO for marking notifications as read.
 */
public class NotificationReadRequest {

    @NotEmpty
    private List<Long> notificationIds;

    public NotificationReadRequest() {}

    public List<Long> getNotificationIds() { return notificationIds; }
    public void setNotificationIds(List<Long> notificationIds) { this.notificationIds = notificationIds; }
}
