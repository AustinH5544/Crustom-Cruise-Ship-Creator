package cruiseModel;

import java.util.ArrayList;
import java.util.List;

public class Cruise {
    private CruisePort startingPort;
    private List<CruisePort> intermediatePorts;
    private CruisePort destinationPort;
    private final String departureDate = "07/01/2023";
    private final int daysInIntermediatePort = 1;
    private int daysOnWater;

    public void setDaysOnWater(final int daysOnWater) {
        this.daysOnWater = daysOnWater;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public int getDaysInIntermediatePort() {
        return daysInIntermediatePort;
    }

    public int getDaysOnWater() {
        return daysOnWater;
    }

    public Cruise(final CruisePort startingPort) {
        this.startingPort = startingPort;
        this.intermediatePorts = new ArrayList<>();
    }

    public void addIntermediatePort(final CruisePort intermediatePort) {
        intermediatePorts.add(intermediatePort);
    }

    public CruisePort getStartingPort() {
        return this.startingPort;
    }

    public void setStartingPort(final CruisePort startingPort) {
        this.startingPort = startingPort;
    }

    public List<CruisePort> getIntermediatePorts() {
        return this.intermediatePorts;
    }

    public CruisePort getDestinationPort() {
        return this.destinationPort;
    }

    public void setDestinationPort(final CruisePort destinationPort) {
        this.destinationPort = destinationPort;
    }

    public void printDetails() {
        System.out.println("Starting Port: " + this.startingPort.getCountry() + ", " + this.startingPort.getName());

        if (!this.intermediatePorts.isEmpty()) {
            System.out.println("Intermediate Ports:");
            for (CruisePort intermediatePort : this.intermediatePorts) {
                System.out.println("- " + intermediatePort.getCountry() + ", " + intermediatePort.getName()
                        + "\nDays in " + intermediatePort.getCountry() + ", " + intermediatePort.getName() + ": " + this.daysInIntermediatePort);
            }
        }

        System.out.println("Destination Port: " + this.destinationPort.getCountry() + ", " + this.destinationPort.getName());
        System.out.println("Total Days on water: " + this.daysOnWater);
        System.out.println("Departure Date: " + this.departureDate);
    }


}

