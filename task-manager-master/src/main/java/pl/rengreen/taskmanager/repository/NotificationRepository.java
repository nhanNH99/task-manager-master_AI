package pl.rengreen.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.rengreen.taskmanager.model.Notification;

import java.util.List;

/**
 * Repository for Notification entity.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findByUserIdAndDeletedFalseOrderByCreatedAtDesc(Long userId, Pageable pageable);

    List<Notification> findByIdInAndUserIdAndDeletedFalse(List<Long> ids, Long userId);
}
