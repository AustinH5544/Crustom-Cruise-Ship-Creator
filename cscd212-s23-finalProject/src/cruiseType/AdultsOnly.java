package cruiseType;

import strategy.CruiseTypeStrategy;

public class AdultsOnly implements CruiseTypeStrategy {

    public void printDetails() {
        System.out.println("- Adults Only -");
    }

    @Override
    public String getType() {
        return "Adults Only";
    }
}
