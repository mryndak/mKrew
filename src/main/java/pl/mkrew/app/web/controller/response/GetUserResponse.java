package pl.mkrew.app.web.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mkrew.app.dto.UserDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GetUserResponse {

    private List<UserDto> users;
}
