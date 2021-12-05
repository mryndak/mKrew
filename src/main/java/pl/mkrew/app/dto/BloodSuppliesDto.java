package pl.mkrew.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodSuppliesDto {

    private Long id;
    private LocalDate localDate;
    private BloodGroup bloodGroup;
    private BloodLevel bloodLevel;

    @ManyToOne
    private RCKiKDto rcKiKDto;
}
