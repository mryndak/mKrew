package pl.mkrew.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkrew.app.domain.BloodSupplies;

import java.util.Optional;

@Repository
public interface BloodSuppliesRepository extends JpaRepository<BloodSupplies, Long> {

//    Optional<BloodSupplies> findByBloodGroup();
//
//    Optional<BloodSupplies> findByRcKiK(String rcKiK);
//
//    Optional<BloodSupplies> findByBloodLevel(String bloodLevel);

}
