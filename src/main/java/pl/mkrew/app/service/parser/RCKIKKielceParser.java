package pl.mkrew.app.service.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.Map;
import java.util.stream.Collectors;

import static pl.mkrew.app.domain.BloodGroup.getBloodGroupByName;

public class RCKIKKielceParser implements BloodSuppliesParser {

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "0", BloodLevel.L_0,
            "25.gif", BloodLevel.L_25,
            "501.png", BloodLevel.L_50,
            "75.png", BloodLevel.L_75,
            "100.png", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Map<BloodGroup, BloodLevel> data = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("sppb-img-responsive")
                .stream()
                .flatMap(v -> v.getAllElements().stream())
                .map(v -> v.toString())
                .filter(p -> p.contains("krople") && !p.contains("Image"))
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toMap(
                        s -> getBloodGroupByName(s.substring(s.indexOf("alt=") + 5, s.indexOf("title") - 2)),
                        s -> bloodLevelMap.get(s.substring(s.indexOf("krople/") + 7, s.indexOf("alt") - 2))
                ));

        return data;
    }
}
