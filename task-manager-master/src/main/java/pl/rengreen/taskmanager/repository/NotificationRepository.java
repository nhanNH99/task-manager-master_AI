package pl.rengreen.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.rengreen.taskmanager.model.Notification;
import pl.rengreen.taskmanager.model.User;

import java.util.Optional;

/**
 * Repository for Notification entity.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findByUserOrderByScheduledAtDesc(User user, Pageable pageable);

    Optional<Notification> findByIdAndUser(Long id, User user);
}
