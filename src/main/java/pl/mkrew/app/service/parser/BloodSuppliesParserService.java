package pl.mkrew.app.service.parser;

import org.springframework.stereotype.Service;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;
import pl.mkrew.app.util.BloodSuppliesParserName;

import java.util.Map;

@Service
public class BloodSuppliesParserService {

    private final Map<BloodSuppliesParserName, BloodSuppliesParser> parsers = Map.of(
            BloodSuppliesParserName.RCKIK_RZESZOW, new RCKIKRzeszowParser()
    );

    public Map<BloodGroup, BloodLevel> fetchData(BloodSuppliesParserName parserName, String website) {
        return parsers.get(parserName)
                .fetchData(website);
    }
}