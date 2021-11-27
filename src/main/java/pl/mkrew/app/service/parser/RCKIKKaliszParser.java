package pl.mkrew.app.service.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RCKIKKaliszParser implements BloodSuppliesParser {

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "0", BloodLevel.L_0,
            "Bardzo niski", BloodLevel.L_25,
            "Niski", BloodLevel.L_50,
            "Wysoki", BloodLevel.L_75,
            "100", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements bloodDropTitle = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("blood-drop-title");

        Elements bloodDropText = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("blood-drop-text");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();

        List<String> bloodGroups = bloodDropTitle.eachText()
                .stream()
                .filter(o -> o.contains("RH"))
                .collect(Collectors.toList());

        List<String> bloodLevels = bloodDropText.eachText();

        for (int i = 0; i < bloodGroups.size(); i++) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i));
            data.put(group, level);
        }

        return data;
    }
}
