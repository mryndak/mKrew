package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;
import pl.mkrew.app.domain.BloodSupplies;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.dto.BloodSuppliesDto;
import pl.mkrew.app.repository.BloodSuppliesRepository;
import pl.mkrew.app.repository.RCKiKRepository;
import pl.mkrew.app.service.parser.BloodSuppliesParserService;
import pl.mkrew.app.util.BloodSuppliesParserName;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BloodSuppliesService {

    private final RCKiKRepository rckikRepository;
    private final BloodSuppliesRepository bloodSuppliesRepository;
    private final BloodSuppliesParserService parserService;

    public void refresh() {
        List<RCKiK> rcKiKList = rckikRepository.findAll();

        Map<BloodSuppliesParserName, String> websites = rcKiKList
                .stream()
                .collect(Collectors.toMap(
                        RCKiK::getParserName,
                        RCKiK::getWebsite
                ));

        Map<BloodSuppliesParserName, RCKiK> rcKiKMap = rcKiKList
                .stream()
                .collect(Collectors.toMap(
                        RCKiK::getParserName,
                        v -> v
                ));

        websites.forEach((parserName, website) -> CompletableFuture.runAsync(() -> {
            Map<BloodGroup, BloodLevel> result = parserService.fetchData(parserName, website);
            log.info("Fetch data for: {}, result: {}", parserName, result);
            List<BloodSupplies> bloodSuppliesList = result.entrySet().stream()
                    .map(entry -> {
                        return BloodSupplies.builder()
                                .bloodGroup(entry.getKey())
                                .bloodLevel(entry.getValue())
                                .lastUpdated(LocalDate.now())
                                .rckik(rcKiKMap.get(parserName))
                                .build();
                    }).collect(Collectors.toList());
            bloodSuppliesRepository.saveAll(bloodSuppliesList);
        }));
    }

    public void addBloodLevel(BloodSuppliesDto bloodSuppliesDto) {
        BloodSupplies bloodSupplies = BloodSupplies.builder()
                .bloodGroup(bloodSuppliesDto.getBloodGroup())
                .bloodLevel(bloodSuppliesDto.getBloodLevel())
                .build();
        bloodSuppliesRepository.save(bloodSupplies);
    }
}
