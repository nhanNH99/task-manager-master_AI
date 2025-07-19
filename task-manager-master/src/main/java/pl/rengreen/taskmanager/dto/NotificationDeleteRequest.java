package pl.rengreen.taskmanager.dto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * DTO for deleting notifications.
 */
public class NotificationDeleteRequest {

    @NotEmpty
    private List<Long> notificationIds;

    public NotificationDeleteRequest() {}

    public List<Long> getNotificationIds() { return notificationIds; }
    public void setNotificationIds(List<Long> notificationIds) { this.notificationIds = notificationIds; }
}
