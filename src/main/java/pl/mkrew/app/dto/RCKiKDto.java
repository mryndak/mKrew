package pl.mkrew.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RCKiKDto {

    private Long id;
    private String name;
    private String city;
    private String phoneNumber;
    private String website;

    public String getCity() {
        return city;
    }
}
