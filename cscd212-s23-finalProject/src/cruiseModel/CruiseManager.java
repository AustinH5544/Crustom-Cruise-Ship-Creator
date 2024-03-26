package cruiseModel;

import builder.CruiseBuilder;
import builder.CruiseShipBuilder;

public class CruiseManager {

    private CruiseBuilder cruiseBuilder;
    private Cruise cruise;
    private CruiseShip cruiseShip;
    private CruiseShipBuilder cruiseShipBuilder;
    private Client client;

    public CruiseManager() {
        this.cruiseBuilder = new CruiseBuilder();
        this.cruiseShipBuilder = new CruiseShipBuilder();
        this.client = new Client("Austin");
    }

    public void createCruise() {
        this.cruise = cruiseBuilder.createCruisePort();
        this.cruiseShip = cruiseShipBuilder.createCruiseShip();
        System.out.println();
        System.out.println("Cruise booked!");
        System.out.println();
    }

    public CruiseShipBuilder getCruiseShipBuilder() {
        return cruiseShipBuilder;
    }

    public Client getClient() {
        return client;
    }

    public CruiseBuilder getCruiseBuilder() {
        return cruiseBuilder;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public CruiseShip getCruiseShip() {
        return cruiseShip;
    }

    public void displayCruiseSystemDetails() {
        this.cruiseShip.printShipDetails();
        this.cruise.printDetails();
        this.cruiseShip.printCost();
    }
}
