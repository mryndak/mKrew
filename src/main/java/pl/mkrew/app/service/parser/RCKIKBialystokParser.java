package pl.mkrew.app.service.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RCKIKBialystokParser implements BloodSuppliesParser{

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "zeroBlood", BloodLevel.L_0,
            "minBlood", BloodLevel.L_25,
            "midBLood", BloodLevel.L_50,
            "maxBlood", BloodLevel.L_75,
            "stopBlood", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements bloodBankChart = Jsoup.connect(website)
                .get()
                .body()
                .select("#bloodBankChart");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();
        List<String> bloodGroups = bloodBankChart.dataNodes();
        List<String> bloodLevels = bloodBankChart.select("style")
                .eachAttr("class");

        for(int i = 0; i < bloodGroups.size(); i++ ) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i));
            data.put(group, level);
        }

        return data;
    }
}