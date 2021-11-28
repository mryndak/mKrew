package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.UserEntity;
import pl.mkrew.app.dto.UserDto;
import pl.mkrew.app.mapper.UserMapper;
import pl.mkrew.app.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EmailService emailService;

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<UserDto> getAllUsers() {
        log.info("Get users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public void addUser(UserDto userDto) {
        log.info("add user");
        UserEntity user = userMapper.mapToUser(userDto);
        UUID confiramtionId = UUID.randomUUID();
        LocalDateTime validTo = LocalDateTime.now().plusMinutes(15);
        user.setConfirmationId(confiramtionId);
        user.setValidTo(validTo);
        String passwordHash = encoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
        emailService.sendEmail(userDto.getEmail(), "Witam w mKrew", "Witaj "
                + user.getName()
                + ". Założyłeś konto w serwisie mKrew. Przesyłamy link aktywacyjny, który jest ważny 15min "
                + "http://localhost:8080/v1/user/confirmation/"
                + user.getConfirmationId());
    }

    public Optional<UserDto> getUser(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapToDTO);
    }

    public boolean checkExistUser(String login, String email) {
        return userRepository.existsByEmailOrLogin(login, email);
    }

    public void confirmUser(UUID confirmationId) {
        Optional<pl.mkrew.app.domain.UserEntity> user = userRepository.findByConfirmationId(confirmationId);
        user.ifPresent(u -> {
            u.setConfirmationStatus(true);
            userRepository.save(u);
        });
        user.orElseThrow();
    }

    public void changePasswordForUser(Long userId, String oldPassword, String newPassword, UserDto userDto) {
        UserEntity user = userRepository.findById(userId)
                .get();
        if (encoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Błędne stare hasło");
        }
    }

    public void changePersonalData(Long userId,UserDto userDto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow();
        user.setSurname(userDto.getSurname());
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRckik(userDto.getRckik());
        userRepository.save(user);

    }

    public Optional findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public Optional findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }


    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public void deleteUser(Long userId) {
        Optional<pl.mkrew.app.domain.UserEntity> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            u.setConfirmationStatus(false);
            u.setEnabled(false);
            u.setRoles(null);
            userRepository.save(u);
        });
        user.orElseThrow();

    }
}
