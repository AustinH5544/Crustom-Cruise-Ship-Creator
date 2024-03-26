package cruiseModel;

import cruiseRooms.CruiseRoom;
import strategy.CruisePackageStrategy;
import strategy.CruiseTypeStrategy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CruiseShip {
    private String parentCompany;
    private List<String> shipNames;
    private CruiseTypeStrategy shipTypeStrategy;
    private List<CruisePackageStrategy> packages;
    private CruiseRoom assignedRoom;

    public CruiseShip(final String parentCompany, final String[] shipNames) {
        this.parentCompany = parentCompany;
        this.shipNames = Arrays.asList(shipNames);
        this.packages = new ArrayList<>();
    }

    public String getParentCompany() {
        return this.parentCompany;
    }

    public List<String> getShipNames() {
        return this.shipNames;
    }

    public void setShipTypeStrategy(final CruiseTypeStrategy shipTypeStrategy) {
        this.shipTypeStrategy = shipTypeStrategy;
    }

    public CruiseTypeStrategy getShipTypeStrategy() {
        return this.shipTypeStrategy;
    }

    public void setPackages(final List<CruisePackageStrategy> packages) {
        this.packages = packages;
    }

    public List<CruisePackageStrategy> getPackages() {
        return this.packages;
    }

    public CruiseRoom getAssignedRoom() {
        return this.assignedRoom;
    }

    public void setAssignedRoom(final CruiseRoom assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    public void printShipDetails() {
        System.out.println("Selected cruise line: " + getParentCompany());
        System.out.println("Ship name: " + String.join(", ", getShipNames()));
        System.out.println("Ship type: " + getShipTypeStrategy().getType());
        System.out.println("Packages: ");
        for (CruisePackageStrategy packageStrategy : getPackages()) {
            System.out.println("- " + packageStrategy.getDescription());
        }
        System.out.println("Assigned room: " + getAssignedRoom().getRoomType());
        System.out.println("Room number: " + getAssignedRoom().getLevel() + getAssignedRoom().getRoomNumber());

    }

    public void printCost() {
        System.out.println("Total cost: $" + getCost());
    }

    public void printSubscriberMessage() {
        System.out.println("We offer a special subscriber program. If the price of your cruise room drops\n" +
                "before you depart on your cruise, we will refund you the difference in price.\n" +
                "Since you booked a cruise with us, you are automatically enrolled in this program.\n");
    }

    public String getCost() {
        double cost = 0;
        for (CruisePackageStrategy packageStrategy : getPackages()) {
            cost += packageStrategy.calculatePrice();
        }
        cost += getAssignedRoom().getCost();

        // Creating a DecimalFormat instance to format the cost
        DecimalFormat df = new DecimalFormat("#.00");

        return df.format(cost);
    }
}
