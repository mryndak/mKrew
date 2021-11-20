package pl.mkrew.app.mapper;

import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.dto.RCKiKDto;

@Service
public class RCKiKMapper {

    public RCKiKDto mapToDto(RCKiK rcKiK) {
        return RCKiKDto.builder()
                .id(rcKiK.getId())
                .name(rcKiK.getName())
                .city(rcKiK.getCity())
                .phoneNumber(rcKiK.getPhoneNumber())
                .website(rcKiK.getWebsite())
                .build();
    }

    public RCKiK mapToRCKiK(RCKiKDto rcKiKDto) {
        return RCKiK.builder()
                .name(rcKiKDto.getName())
                .city(rcKiKDto.getCity())
                .phoneNumber(rcKiKDto.getPhoneNumber())
                .website(rcKiKDto.getWebsite())
                .build();
    }
}
