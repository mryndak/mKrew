package pl.mkrew.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.mkrew.app.dto.UserDto;
import pl.mkrew.app.response.GetUserDetails;
import pl.mkrew.app.service.UserService;
import pl.mkrew.app.web.controller.request.ChangePersonalDataRequest;
import pl.mkrew.app.web.controller.request.ChangePasswordRequest;
import pl.mkrew.app.web.controller.response.GetUserResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public GetUserResponse getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return GetUserResponse.of(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDetails> getUsersDetails(@PathVariable Long id) {
       Optional<UserDto> user = userService.getUser(id);
        return user.map(userDto -> ResponseEntity.ok(new GetUserDetails(userDto)))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @GetMapping("/confirmation/{confirmationId}")
    public void confirmEmail(@PathVariable UUID confirmationId) {
        userService.confirmUser(confirmationId);
        System.out.println("Potwierdzony");
    }

    @PostMapping("/update/{userId}")
    private void changePersonalDataAndInfo(@PathVariable("userId") Long userId, @RequestBody ChangePersonalDataRequest request){
        userService.changePersonalData(userId, request.getUserDto());
    }


    @PostMapping("/change/{userId}")
    private void changeUserPassword(@PathVariable("userId")Long userId, String oldPassword, String newPassword, @RequestBody ChangePasswordRequest request) {
        userService.changePasswordForUser(userId, oldPassword, newPassword, request.getUserDto());
    }

    @DeleteMapping("/{id}")
    public void disableUser(@PathVariable("id") Long userId) {

        userService.deleteUser(userId);
    }

}
