package pl.mkrew.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    private boolean confirmationStatus = false;

    @Column(unique = true)
    private UUID confirmationId;

    private LocalDateTime validTo;


}
