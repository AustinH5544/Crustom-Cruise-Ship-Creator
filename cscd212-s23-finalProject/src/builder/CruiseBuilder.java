package builder;

import cruiseModel.Cruise;
import cruiseModel.CruisePort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CruiseBuilder {
    private List<CruisePort> cruisePorts;

    public CruiseBuilder() {
        this.cruisePorts = loadCruisePorts();
    }

    private List<CruisePort> loadCruisePorts() {
        List<CruisePort> ports = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("ports.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                String country = parts[0];
                String name = parts[1];
                CruisePort cruisePort = new CruisePort(country, name);
                ports.add(cruisePort);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Failed to load cruise ports from the file.");
            // Handle file not found exception
        }

        return ports;
    }

    public Cruise createCruisePort() {
        List<CruisePort> availablePorts = new ArrayList<>(cruisePorts);
        List<CruisePort> selectedPorts = new ArrayList<>();

        int startingPortIndex;
        do {
            System.out.println("Available starting ports:");
            displayAvailableCruisePorts(availablePorts);
            startingPortIndex = getUserInput("Select the starting port (select the number associated with port): ", availablePorts.size()) - 1;
            if (startingPortIndex < 0) {
                System.out.println("Invalid selection. Please select a valid port.");
            }
        } while (startingPortIndex < 0);
        CruisePort startingPort = availablePorts.get(startingPortIndex);
        availablePorts.remove(startingPort);
        selectedPorts.add(startingPort);

        Cruise cruise = new Cruise(startingPort);

        int intermediatePortIndex;
        do {
            System.out.println("Available intermediate ports:");
            displayAvailableCruisePorts(availablePorts);
            intermediatePortIndex = getUserInput("Select an intermediate port (select the number associated with port)\n(Type 0 when finished): ", availablePorts.size());
            if (intermediatePortIndex == 0) {
                break; // User chooses to finish selecting intermediate ports
            }
            CruisePort intermediatePort = availablePorts.get(intermediatePortIndex - 1);
            availablePorts.remove(intermediatePort);
            selectedPorts.add(intermediatePort);
            cruise.addIntermediatePort(intermediatePort);
        } while (true);

        int destinationPortIndex;
        do {
            System.out.println("Available destination ports:");
            displayAvailableCruisePorts(availablePorts);
            destinationPortIndex = getUserInput("Select the destination port (select the number associated with port): ", availablePorts.size()) - 1;
            if (destinationPortIndex < 0) {
                System.out.println("Invalid selection. Please select a valid port.");
            }
        } while (destinationPortIndex < 0);
        CruisePort destinationPort = availablePorts.get(destinationPortIndex);
        selectedPorts.add(destinationPort);
        cruise.setDestinationPort(destinationPort);

        int tempDays = cruise.getIntermediatePorts().isEmpty() ? 4 : ((cruise.getIntermediatePorts().size() + 2) * 2);
        cruise.setDaysOnWater(tempDays);

        return cruise;
    }

    private void displayAvailableCruisePorts(final List<CruisePort> availablePorts) {
        System.out.println("Available ports:");
        for (int i = 0; i < availablePorts.size(); i++) {
            CruisePort port = availablePorts.get(i);
            System.out.println((i + 1) + ". " + port.getCountry() + ", " + port.getName());
        }
    }

    private int getUserInput(final String prompt, final int max) {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
            input = scanner.nextInt();
        } while (input < 0 || input > max);
        return input;
    }
}


