package pl.mkrew.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkrew.app.domain.RCKiK;

@Repository
public interface RCKiKRepository extends JpaRepository<RCKiK, Long> {
}
