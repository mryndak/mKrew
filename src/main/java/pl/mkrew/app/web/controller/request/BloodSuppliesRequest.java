package pl.mkrew.app.web.controller.request;

import lombok.Data;
import pl.mkrew.app.dto.BloodSuppliesDto;

@Data
public class BloodSuppliesRequest {

    private Long id;

    private BloodSuppliesDto bloodSuppliesDto;
}
