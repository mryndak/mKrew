package pl.mkrew.app.domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;
    private String password;

    @Column(unique = true, nullable = false)
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @ManyToOne
    private RCKiK rckik;

    private boolean confirmationStatus;
    @Column(unique = true)
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID confirmationId;
    private LocalDateTime validTo;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    @OneToMany
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany
    private List<Questionnaire> questionnaires = new ArrayList<>();

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "reset_token")
    private String resetToken;

}
