package pl.mkrew.app.domain;

public class BloodSupplies2 {

    private String bloodGroup;
    private String bloodLevel;
    private String city;

    public BloodSupplies2(String bloodGroup, String bloodLevel, String city) {
        this.bloodGroup = bloodGroup;
        this.bloodLevel = bloodLevel;
        this.city = city;
        
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getBloodLevel() {
        return bloodLevel;
    }
    
    public String getCity() {
        return city;
    }
}
