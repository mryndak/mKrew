package pl.mkrew.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.RCKiK;
import pl.mkrew.app.repository.RCKiKRepository;
import pl.mkrew.app.service.parser.BloodSuppliesParserService;

import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BloodSuppliesService {

    private final RCKiKRepository rckikRepository;
    private final BloodSuppliesParserService parserService;

    public void refresh() {
        var websites = rckikRepository.findAll()
                .stream()
                .collect(Collectors.toMap(
                        RCKiK::getParserName,
                        RCKiK::getWebsite
                ));

        websites.forEach((parserName, website) -> {
            var result = parserService.fetchData(parserName, website);
            log.info("Fetch data for: {}, result: {}", parserName, result);
        });

    }
}
