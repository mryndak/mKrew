package pl.mkrew.app.service.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RCKIKKatowiceParser  implements BloodSuppliesParser {

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "0", BloodLevel.L_0,
            "stan bardzo niski", BloodLevel.L_25,
            "stan niski", BloodLevel.L_50,
            "stan średni", BloodLevel.L_75,
            "stan wysoki", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements dropHeadline = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("drop__headline");

        Elements dropLevel = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("drop__level");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();

        List<String> bloodGroups = dropHeadline.eachText();

        List<String> bloodLevels = dropLevel.eachText();

        for (int i = 0; i < bloodGroups.size(); i++) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i));
            data.put(group, level);
        }

        return data;
    }
}
