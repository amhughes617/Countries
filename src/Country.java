/**
 * Created by alexanderhughes on 2/11/16.
 */
public class Country {
    String countryName;
    String abbreviation;

    public Country(String countryName, String abbreviation) {
        this.countryName = countryName;
        this.abbreviation = abbreviation;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}
