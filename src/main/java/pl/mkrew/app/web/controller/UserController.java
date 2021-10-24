package pl.mkrew.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.dto.UserDto;
import pl.mkrew.app.service.UserService;
import pl.mkrew.app.web.controller.response.GetUserResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public GetUserResponse getUsers() {
        List<UserDto> users= userService.getAllUsers();
        return GetUserResponse.of(users);
    }

    @GetMapping("/confirmation/{confirmationId}")
    public void confirmEmail(@PathVariable UUID confirmationId) {
        userService.confirmUser(confirmationId);
    }

}
