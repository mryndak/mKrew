package pl.mkrew.app.mapper;

import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;
import pl.mkrew.app.domain.BloodSupplyForecast;
import pl.mkrew.app.dto.BloodSupplyForecastDto;

@Service
public class BloodSupplyForecastMapper {

    public BloodSupplyForecastDto mapToDto(BloodSupplyForecast bloodSupplyForecast) {
        return BloodSupplyForecastDto.builder()
                .id(bloodSupplyForecast.getId())
                //.localDate(bloodSupplyForecast.getDate())
                .bloodGroup(BloodGroup.valueOf(bloodSupplyForecast.getBloodGroup().name()))
                .bloodLevel(BloodLevel.valueOf(bloodSupplyForecast.getBloodLevel().name()))
                .build();
    }

    public BloodSupplyForecast mapToBloodSupplyForecast(BloodSupplyForecastDto bloodSupplyForecastDto) {
        return BloodSupplyForecast.builder()
                .bloodGroup(BloodGroup.valueOf(bloodSupplyForecastDto.getBloodGroup().name()))
                .bloodLevel(BloodLevel.valueOf(bloodSupplyForecastDto.getBloodLevel().name()))
                .build();
    }
}
