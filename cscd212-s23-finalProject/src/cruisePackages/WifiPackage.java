package cruisePackages;

public class WifiPackage extends CruisePackage {

    public WifiPackage() {
        super(100.00, "Includes unlimited WIFI for the duration of your trip");
    }

    @Override
    public String getPackageName() {
        return "Wifi Package";
    }
}
