package pl.mkrew.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkrew.app.domain.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
