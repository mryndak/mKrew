package pl.mkrew.app.web.controller.response;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.service.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/confirmation/{confirmationId}")
    public void confirmEmail(@PathVariable UUID confirmationId) {
        userService.confirmUser(confirmationId);
    }
}
