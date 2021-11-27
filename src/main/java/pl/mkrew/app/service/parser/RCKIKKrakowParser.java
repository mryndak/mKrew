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

public class RCKIKKrakowParser implements BloodSuppliesParser{

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "0", BloodLevel.L_0,
            "25", BloodLevel.L_25,
            "50", BloodLevel.L_50,
            "75", BloodLevel.L_75,
            "100", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements columnOneFourth = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("column one-fourth");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();
        List<String> bloodGroups = columnOneFourth.eachText();
        List<String> bloodLevels = columnOneFourth.select("img[src$=.png]")
                .eachAttr("src")
                .stream()
                .map(v -> v.replace("https://rckik.krakow.pl/wp-content/uploads/2016/11/x", ""))
                .map(v -> v.substring(0, v.indexOf(".png")))
                .collect(Collectors.toList());

        for(int i = 0; i < bloodGroups.size(); i++ ) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i));
            data.put(group, level);
        }

        return data;
    }
}
