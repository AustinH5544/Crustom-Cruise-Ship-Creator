package cruisePackages;

public class BeveragePackage extends CruisePackage {

    public BeveragePackage() {
        super(300.00, "Unlimited alcoholic drinks package");
    }

    @Override
    public String getPackageName() {
        return "Beverage Package";
    }

}


