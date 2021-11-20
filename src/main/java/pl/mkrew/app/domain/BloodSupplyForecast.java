package pl.mkrew.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class BloodSupplyForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private LocalDate update;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.STRING)
    private BloodLevel bloodLevel;

    @ManyToOne
    private RCKiK rcKiK;
}
