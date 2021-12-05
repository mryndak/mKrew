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

public class RCKIKGdanskParser implements BloodSuppliesParser{

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "5", BloodLevel.L_0,
            "4", BloodLevel.L_25,
            "3", BloodLevel.L_50,
            "2", BloodLevel.L_75,
            "1", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements title = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("title");

        Elements ikon = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("ikon");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();
        List<String> bloodGroups = title.eachText()
                .stream()
                .filter(o -> o.contains("RhD"))
                .collect(Collectors.toList());
        List<String> bloodLevels = ikon.select("img[src$=.png]")
                .eachAttr("src")
                .stream()
                .map(v -> v.replace("images/blood_", ""))
                .map(v -> v.substring(0, v.indexOf(".png")))
                .filter(o -> o.length() < 2)
                .collect(Collectors.toList());

        for (int i = 0; i < bloodGroups.size(); i++) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i));
            data.put(group, level);
        }

        return data;
    }
}
