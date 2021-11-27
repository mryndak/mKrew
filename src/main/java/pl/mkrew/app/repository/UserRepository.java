package pl.mkrew.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import pl.mkrew.app.domain.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmailOrLogin (String login, String email);
    Optional<UserEntity> findByConfirmationId (UUID confirmationId);
    Optional<UserEntity> findUserByLogin (String login);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByResetToken(String resetToken);

}
