package cruiseModel;

public class CruisePort {

    private String country;
    private String name;

    public CruisePort(final String country, final String name) {
        this.country = country;
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
