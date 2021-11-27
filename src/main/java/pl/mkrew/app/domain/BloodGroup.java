package pl.mkrew.app.domain;

import java.util.Arrays;

public enum BloodGroup {

    ZERO_RH_p("0RH+"),
    ZERO_RH_m("0RH-"),
    A_RH_p("ARH+"),
    A_RH_m("ARH-"),
    B_RH_p("BRH+"),
    B_RH_m("BRH-"),
    AB_RH_p("ABRH+"),
    AB_RH_m("ABRH-");

    private final String value;

    BloodGroup(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BloodGroup getBloodGroupByName(String name) {
        String parsedName = name.trim()
                .replace("D", "")
                .replace("–", "-")
                .toUpperCase()
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
