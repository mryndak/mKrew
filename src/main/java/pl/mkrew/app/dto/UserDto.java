package pl.mkrew.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String login;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
}
