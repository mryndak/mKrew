package pl.mkrew.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodSupplyForecastDto {

    private Long id;
    private LocalDate localDate;
    private BloodGroup bloodGroup;
    private BloodLevel bloodLevel;
}
