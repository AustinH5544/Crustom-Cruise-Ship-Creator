package cruisePackages;

import strategy.CruisePackageStrategy;

public abstract class CruisePackage implements CruisePackageStrategy {

    private double cost;
    private String description;

    public CruisePackage(final double cost, final String description) {
        this.cost = cost;
        this.description = description;
    }

    public abstract String getPackageName();

    @Override
    public double calculatePrice() {
        return this.cost;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CruisePackage otherPackage = (CruisePackage) obj;
        return getPackageName().equals(otherPackage.getPackageName());
    }

    @Override
    public int hashCode() {
        return getPackageName().hashCode();
    }

}
