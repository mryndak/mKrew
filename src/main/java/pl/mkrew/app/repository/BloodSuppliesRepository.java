package pl.mkrew.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkrew.app.domain.BloodSupplies;

@Repository
public interface BloodSuppliesRepository extends JpaRepository<BloodSupplies, Long> {
}
