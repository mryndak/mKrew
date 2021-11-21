package pl.mkrew.app.service.parser;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.mkrew.app.domain.BloodGroup;
import pl.mkrew.app.domain.BloodLevel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RCKIKKrakowParser implements BloodSuppliesParser{

    Map<String, BloodLevel> bloodLevelMap = Map.of(
            "", BloodLevel.L_0,
            "25.png", BloodLevel.L_25,
            "50.png", BloodLevel.L_50,
            "5FcGhmtpjy.webp", BloodLevel.L_75,
            "data:image/webp;base64,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",
            BloodLevel.L_100
    );

    @Override
    @SneakyThrows
    public Map<BloodGroup, BloodLevel> fetchData(String website) {

        Elements image_wrapper = Jsoup.connect(website)
                .get()
                .body()
                .getElementsByClass("column one-fourth");

        Map<BloodGroup, BloodLevel> data = new LinkedHashMap<>();
        List<String> bloodGroups = image_wrapper.eachText();
        List<String> bloodLevels = image_wrapper.eachText();

        List<Attributes> attributes = image_wrapper.unwrap()
                        .stream()
                .flatMap(p -> p.getElementsByAttributeValueContaining("src", ".png").stream())
                .map(v -> v.attributes())
                .collect(Collectors.toList());




        for(int i = 0; i < bloodGroups.size(); i++ ) {
            BloodGroup group = BloodGroup.getBloodGroupByName(bloodGroups.get(i));
            BloodLevel level = bloodLevelMap.get(bloodLevels.get(i));
            data.put(group, level);
        }

        return data;
    }
}
