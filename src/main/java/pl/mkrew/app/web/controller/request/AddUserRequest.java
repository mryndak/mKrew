package pl.mkrew.app.web.controller.request;

import com.sun.istack.NotNull;
import lombok.Data;
import net.andreinc.jbvext.annotations.str.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AddUserRequest {

    @NotNull
    @NotEmpty
//    @Size(min = 8, max = 32, message = "Login musi mieć zawierać 8-32 znaków")
    private String login;
//    @Password(message = "Hasło musi zawierać min.8 znaków, wielką literę i znak specjalny")
    private String password;
    @NotEmpty(message = "Pole musi zawierać co najmniej jeden znak")
    private String name;
    @NotEmpty(message = "Pole musi zawierać co najmniej jeden znak")
    private String surname;
    @Email(message = "Pole musi zawierać email")
    private String email;
    @Pattern(regexp = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)", message = "numer telefonu musi zawierać format: 000-000-000")
    private String phoneNumber;
    private String bloodGroup;

}