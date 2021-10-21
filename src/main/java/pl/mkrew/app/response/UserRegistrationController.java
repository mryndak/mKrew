package pl.mkrew.app.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.dto.UserDto;
import pl.mkrew.app.request.AddUserRequest;
import pl.mkrew.app.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/registration")
public class UserRegistrationController {
    private final UserService service;

    @Autowired
    public UserRegistrationController(UserService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request) {
        var isExist = service.checkExistUser(request.getLogin(), request.getEmail());
        if (isExist) {
            return ResponseEntity.unprocessableEntity().build();
        }
        var userDto = UserDto.builder()
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
