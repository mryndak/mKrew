package pl.mkrew.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class UserEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String login;
    private String password;

    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;

    private boolean confirmationStatus = false;
    @Column(unique = true)
    private String confirmationId;
    private LocalDateTime validTo;

    @ElementCollection
    private List<String> bloodGroup = new ArrayList<>();
}
