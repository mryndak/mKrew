package pl.mkrew.app.mapper;

import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;
import pl.mkrew.app.domain.BloodSupplies;
import pl.mkrew.app.dto.BloodSuppliesDto;

@Service
public class BloodSuppliesMapper {

    public BloodSuppliesDto mapToDto(BloodSupplies bloodSupplies) {
        return BloodSuppliesDto.builder()
                .id(bloodSupplies.getId())
                .localDate(bloodSupplies.getLocalDateUpdate())
                .bloodGroup(BloodGroup.valueOf(bloodSupplies.getBloodGroup().name()))
                .bloodLevel(BloodLevel.valueOf(bloodSupplies.getBloodLevel().name()))
                .build();
    }
    public BloodSupplies mapToBloodSupplies(BloodSuppliesDto bloodSuppliesDto) {
        return BloodSupplies.builder()
                .bloodGroup(BloodGroup.valueOf(bloodSuppliesDto.getBloodGroup().name()))
                .bloodLevel(BloodLevel.valueOf(bloodSuppliesDto.getBloodLevel().name()))
                .build();
    }
}
