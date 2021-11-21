package pl.mkrew.app.service.parser;

import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.Map;

public class RCKIKBialystokParser implements BloodSuppliesParser{

    @Override
    public Map<BloodGroup, BloodLevel> fetchData(String website) {
        return null;
    }
}
