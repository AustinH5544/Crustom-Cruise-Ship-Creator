package cruiseType;

import strategy.CruiseTypeStrategy;

public class FamilyFriendly implements CruiseTypeStrategy {

    @Override
    public void printDetails() {
        System.out.println("- Family Friendly -");
    }

    @Override
    public String getType() {
        return "Family Friendly";
    }
}
