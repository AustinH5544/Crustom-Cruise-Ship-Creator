package cruisePackages;

public class BasicPackage extends CruisePackage {

    public BasicPackage() {
        super(50.00, "Includes buffet food and fountain drinks");
    }

    @Override
    public String getPackageName() {
        return "Basic Package";
    }

}

