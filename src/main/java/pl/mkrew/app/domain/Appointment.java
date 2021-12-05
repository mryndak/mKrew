package pl.mkrew.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    private boolean available;
    private boolean confirmVisit;

    @ManyToOne
    private RCKiK rckik;

    @ManyToOne
    private UserEntity user;

    @OneToOne
    private Questionnaire questionnaire;

}
