package pl.mkrew.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mkrew.app.domain.BloodSupplies;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.dto.BloodSuppliesDto;
import pl.mkrew.app.repository.BloodSuppliesRepository;

@RestController
@RequestMapping(value = "/v1/user")
public class BloodSuppliesController {

    @Autowired
    BloodSuppliesRepository bloodSuppliesRepository;

    @PostMapping
    public void saveBloodSupplies(@RequestBody BloodSuppliesDto bloodSuppliesDto) {

        BloodSupplies bloodSupplies= new BloodSupplies();

        bloodSupplies.setBloodGroup(bloodSuppliesDto.getBloodGroup());
        bloodSupplies.setBloodLevel(bloodSuppliesDto.getBloodLevel());
        bloodSupplies.setLocalDateUpdate(bloodSuppliesDto.getLocalDate());
        bloodSupplies.getRcKiK().getCity();

        bloodSuppliesRepository.save(bloodSupplies);
    }
}
