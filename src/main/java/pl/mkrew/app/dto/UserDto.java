package pl.mkrew.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mkrew.app.domain.Appointment;
import pl.mkrew.app.domain.Questionnaire;
import pl.mkrew.app.domain.RCKiK;

import java.time.LocalDateTime;
import java.util.*;

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
    private String bloodGroup;
    private RCKiK rckik;
    private boolean confirmationStatus;
    private UUID confirmationId;
    private LocalDateTime validTo;
    private Set<String> roles = new HashSet<>();
    private List<Appointment> appointments = new ArrayList<>();
    private List<Questionnaire> questionnaires = new ArrayList<>();
    private boolean enabled;
    private String resetToken;
}
