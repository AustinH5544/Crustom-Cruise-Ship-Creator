import cruiseModel.CruiseManager;


public class CruiseMain {

    public static void main(String[] args){

        CruiseManager cruiseManager = new CruiseManager();
        cruiseManager.createCruise();
        cruiseManager.displayCruiseSystemDetails();
        cruiseManager.getCruiseShip().printSubscriberMessage();
        cruiseManager.getClient().add(cruiseManager.getCruiseShip().getAssignedRoom());
        cruiseManager.getCruiseShip().getAssignedRoom().update(200.00);

    }
}
