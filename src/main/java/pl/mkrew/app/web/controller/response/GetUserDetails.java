package pl.mkrew.app.web.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mkrew.app.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDetails {

    private UserDto user;
}
