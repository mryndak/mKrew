package pl.mkrew.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.service.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public GetUserResponse getUsers() {
        var users = userService.getAllUsers();
        return GetUserResponse.of(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDetails> getUsersDetails(@PathVariable Long id) {
        var user = userService.getUser(id);
        return user.map(userDto -> ResponseEntity.ok(new GetUserDetails(userDto)))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @GetMapping("/confirmation/{confirmationId}")
    public void confirmEmail(@PathVariable UUID confirmationId) {
        userService.confirmUser(confirmationId);
    }
}
