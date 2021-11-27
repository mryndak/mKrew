package pl.mkrew.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class RCKiK {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String city;
    private String phoneNumber;
    private String website;

    @OneToMany
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany
    private List<Questionnaire> questionnaire = new ArrayList<>();

}
