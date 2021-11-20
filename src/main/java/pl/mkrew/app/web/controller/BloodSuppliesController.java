package pl.mkrew.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mkrew.app.domain.BloodSupplies;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.dto.BloodSuppliesDto;
import pl.mkrew.app.repository.BloodSuppliesRepository;
import pl.mkrew.app.service.BloodSuppliesService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/blood-supplies")
public class BloodSuppliesController {

    private final BloodSuppliesService bloodSuppliesService;

    @GetMapping("/refresh")
    public ResponseEntity<Void> refreshBloodSupplies() {
        bloodSuppliesService.refresh();
        return ResponseEntity.noContent().build();
    }
}
