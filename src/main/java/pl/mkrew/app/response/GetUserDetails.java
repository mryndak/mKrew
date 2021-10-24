package pl.mkrew.app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.mkrew.app.dto.UserDto;

@AllArgsConstructor
@Data
public class GetUserDetails {
    private UserDto user;
}
