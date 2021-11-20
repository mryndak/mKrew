package pl.mkrew.app.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum BloodGroup {

    ZERO_RH_p("0 RhD+"),
    ZERO_RH_m("0 RhD-"),
    A_RH_p("A RhD+"),
    A_RH_m("A RhD-"),
    B_RH_p("B RhD+"),
    B_RH_m("B RhD-"),
    AB_RH_p("AB RhD+"),
    AB_RH_m("AB RhD-");

    private final String value;

    BloodGroup(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BloodGroup getBloodGroupByName(String name) {
        return Arrays.stream(BloodGroup.values()).filter(p -> name.equals(p.getValue()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
