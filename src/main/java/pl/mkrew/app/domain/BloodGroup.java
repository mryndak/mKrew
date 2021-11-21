package pl.mkrew.app.domain;

import java.util.Arrays;

public enum BloodGroup {

    ZERO_RH_p("0Rh+"),
    ZERO_RH_m("0Rh-"),
    A_RH_p("ARh+"),
    A_RH_m("ARh-"),
    B_RH_p("BRh+"),
    B_RH_m("BRh-"),
    AB_RH_p("ABRh+"),
    AB_RH_m("ABRh-");

    private final String value;

    BloodGroup(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BloodGroup getBloodGroupByName(String name) {
        String parsedName = name.trim().replace("D", "")
                .replace("–", "-")
                .replaceAll(" ", "");
        return Arrays.stream(BloodGroup.values()).filter(p -> parsedName.equals(p.getValue()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
