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

public class RCKIKBydgoszczParser implements BloodSuppliesParser{

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            ": brak", BloodLevel.L_0,
            ": bardzo potrzebujemy", BloodLevel.L_25,
            ": umiarkowane zapotrzebowanie", BloodLevel.L_50,
            ": mamy zapasy", BloodLevel.L_75,
            ": stop", BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements kropelki = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("kropelki").select("li");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();
        List<String> bloodGroups = kropelki.eachText()
                .stream()
                .map(v -> v.substring(0, v.indexOf(":")))
                .collect(Collectors.toList());

        List<String> bloodLevels = kropelki.eachText()
                .stream()
                .map(v -> v.substring(v.indexOf(":")))
                .collect(Collectors.toList());

        for(int i = 0; i < bloodGroups.size(); i++ ) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i));
            data.put(group, level);
        }

        return data;
    }
}
