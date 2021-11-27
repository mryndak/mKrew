package pl.mkrew.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.domain.UserEntity;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private boolean available;
    private Long rckikId;
    private Long userId;
}
