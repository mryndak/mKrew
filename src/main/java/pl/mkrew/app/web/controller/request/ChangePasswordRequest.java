package pl.mkrew.app.web.controller.request;

import lombok.Data;
import pl.mkrew.app.dto.UserDto;

@Data
public class ChangePasswordRequest {

    UserDto userDto;
}
