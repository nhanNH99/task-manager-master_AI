package pl.rengreen.taskmanager.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a notification for a user related to a task.
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who receives the notification.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The task associated with the notification.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Notification message.
     */
    @Column(nullable = false)
    private String message;

    /**
     * Time the notification is scheduled or sent.
     */
    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    /**
     * Whether the notification is recurring.
     */
    @Column(nullable = false)
    private boolean recurring;

    /**
     * Read/unread status.
     */
    @Column(nullable = false)
    private boolean read;

    /**
     * Recurrence type (e.g., DAILY, NONE).
     */
    @Column(length = 20)
    private String recurrenceType;
}
