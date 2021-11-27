package pl.mkrew.app.service.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.*;

public class RCKIKBialystokParser implements BloodSuppliesParser{

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();

        Elements bloodBankChart = Jsoup.connect(website)
                .get()
                .body()
                .select("#bloodBankChart");

        Objects.requireNonNull(bloodBankChart.first()).getElementsByClass("maxBlood")
                .stream()
                .map(Element::text)
                .forEach(bloodGroup -> {
                    data.put(
                            BloodGroup.getBloodGroupByName(bloodGroup),
                            BloodLevel.L_100
                    );
                });

        Objects.requireNonNull(bloodBankChart.first()).getElementsByClass("midBlood")
                .stream()
                .map(Element::text)
                .forEach(bloodGroup -> {
                    data.put(
                            BloodGroup.getBloodGroupByName(bloodGroup),
                            BloodLevel.L_50
                    );
                });

        Objects.requireNonNull(bloodBankChart.first()).getElementsByClass("minBlood")
                .stream()
                .map(Element::text)
                .forEach(bloodGroup -> {
                    data.put(
                            BloodGroup.getBloodGroupByName(bloodGroup),
                            BloodLevel.L_25
                    );
                });

        return data;
    }
}