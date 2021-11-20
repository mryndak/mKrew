package pl.mkrew.app.service.parser;

import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.Map;

public interface BloodSuppliesParser {

    Map<BloodGroup, BloodLevel> fetchData(final String website);
}
