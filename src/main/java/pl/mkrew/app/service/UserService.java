package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<UserDto> getAllUsers() {
        log.info("Get users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public void addUser(UserDto userDto) {
        log.info("add user");
        var user = userMapper.mapToUser(userDto);
        var confiramtionId = UUID.randomUUID().toString();
        var validTo = LocalDateTime.now().plusMinutes(15);
        user.setConfirmationId(confiramtionId);
        user.setValidTo(validTo);
        var passwordHash = encoder.encode(user.getPassword());
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
        return userRepository.existsByConfirmEmailOrLogin(login, email);
    }
    public void confirmUser(String confirmationId) {
        var user = userRepository.findByConfirmationId(confirmationId);
        user.ifPresent(u -> {
            u.setConfirmationStatus(true);
            userRepository.save(u);
        });
    }
}
