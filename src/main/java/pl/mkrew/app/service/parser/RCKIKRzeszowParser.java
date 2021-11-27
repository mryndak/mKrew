package pl.mkrew.app.service.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class RCKIKRzeszowParser implements BloodSuppliesParser {

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "50", BloodLevel.L_0,
            "43", BloodLevel.L_25,
            "35", BloodLevel.L_50,
            "22", BloodLevel.L_75,
            "4", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements iconBloods = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("iconBlood");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();
        List<String> bloodGroups = iconBloods.eachText();
        List<String> bloodLevels = iconBloods.eachAttr("style");

        for(int i = 0; i < bloodGroups.size(); i++ ) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i).replaceAll("background-position: 0 ", "")
                    .replace("px;", ""));
            data.put(group, level);
        }

        return data;
    }
}
