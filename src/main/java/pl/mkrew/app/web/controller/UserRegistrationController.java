package pl.mkrew.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.dto.UserDto;
import pl.mkrew.app.web.controller.request.AddUserRequest;
import pl.mkrew.app.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user/registration")
public class UserRegistrationController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request) {
        boolean isExist = service.checkExistUser(request.getLogin(), request.getEmail());
        if (isExist) {
            return ResponseEntity.unprocessableEntity().build();
        }
        UserDto userDto = UserDto.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .surname(request.getSurname())
                .password(request.getPassword())
                .bloodGroup(request.getBloodGroup())
                .build();
        service.addUser(userDto);
        return ResponseEntity.ok().build();
    }
}
