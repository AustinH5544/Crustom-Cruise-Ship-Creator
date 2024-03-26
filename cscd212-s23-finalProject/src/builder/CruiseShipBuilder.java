package builder;

import cruiseModel.CruiseShip;
import cruisePackages.BasicPackage;
import cruisePackages.BeveragePackage;
import cruisePackages.WifiPackage;
import cruiseRooms.CruiseRoom;
import cruiseType.AdultsOnly;
import cruiseType.FamilyFriendly;
import factory.CruiseRoomFactory;
import strategy.CruisePackageStrategy;
import strategy.CruiseTypeStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class CruiseShipBuilder {
    private List<String[]> cruiseShips;

    public CruiseShipBuilder() {
        this.cruiseShips = loadCruiseShips();
    }

    private List<String[]> loadCruiseShips() {
        List<String[]> shipsList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("ships.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] shipData = line.split(", ");
                    shipsList.add(shipData);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Failed to load cruise ships from the file.");
            // Handle file not found exception
        }

        return shipsList;
    }

    public CruiseShip createCruiseShip() {
        int shipIndex;
        do {
            System.out.println("Available cruise lines:");
            displayCruiseShips();
            shipIndex = getUserInput("Select the cruise line (select the number associated with the cruise line): ", cruiseShips.size()) - 1;
            if (shipIndex < 0) {
                System.out.println("Invalid selection. Please select a valid cruise line.");
            }
        } while (shipIndex < 0);

        String[] shipData = cruiseShips.get(shipIndex);
        String parentCompany = shipData[0];

        List<String> shipNames = new ArrayList<>();
        for (int i = 1; i < shipData.length; i++) {
            shipNames.add(shipData[i]);
        }

        System.out.println("Available ships for " + parentCompany + ":");
        for (int i = 0; i < shipNames.size(); i++) {
            System.out.println((i + 1) + ". " + shipNames.get(i));
        }

        int shipNameIndex;
        do {
            shipNameIndex = getUserInput("Select the ship name (select the number associated with the ship name): ", shipNames.size()) - 1;
            if (shipNameIndex < 0) {
                System.out.println("Invalid selection. Please select a valid ship name.");
            }
        } while (shipNameIndex < 0);

        String shipName = shipNames.get(shipNameIndex);

        CruiseShip selectedShip = new CruiseShip(parentCompany, new String[]{shipName});

        // Prompt for ship type
        CruiseTypeStrategy shipType = selectShipType();
        selectedShip.setShipTypeStrategy(shipType);

        // Prompt for packages
        List<CruisePackageStrategy> selectedPackages = selectPackages();
        selectedShip.setPackages(selectedPackages);

        // Prompt for room
        CruiseRoom selectedRoom = selectRoom();
        selectedShip.setAssignedRoom(selectedRoom);

        return selectedShip;
    }


    private CruiseTypeStrategy selectShipType() {
        int option;
        do {
            option = getUserInput("Ship Types:\n1. Adult Only\n2. Family Friendly\nSelect the ship type " +
                    "(select the number associated with the ship type):", 2);
            if (option <= 0) {
                System.out.println("Invalid selection. Please select a valid option.");
            }
        } while (option <= 0);

        return switch (option) {
            case 1 -> new AdultsOnly();
            case 2 -> new FamilyFriendly();
            default -> null;
        };
    }

    private List<CruisePackageStrategy> selectPackages() {
        DecimalFormat df = new DecimalFormat("#.00");
        List<CruisePackageStrategy> packages = new ArrayList<>();
        List<CruisePackageStrategy> selectedPackages = new ArrayList<>();

        int option;
        do {
            System.out.println("Select a package to add:");
            System.out.println("1. Basic Package - Cost: $" + df.format(new BasicPackage().calculatePrice()) + " (Includes buffet meals and fountain drinks)");
            System.out.println("2. Beverage Package - Cost: $" + df.format(new BeveragePackage().calculatePrice()) + " (Includes alcohol)");
            System.out.println("3. WiFi Package - Cost: $" + df.format(new WifiPackage().calculatePrice()) + " (Includes unlimited WiFi)");
            System.out.println("0. Done");

            option = getUserInput("Enter your choice: ", 3);
            switch (option) {
                case 1:
                    if (!selectedPackages.contains(new BasicPackage())) {
                        packages.add(new BasicPackage());
                        selectedPackages.add(new BasicPackage());
                    } else {
                        System.out.println("Package already selected. Please choose another package.");
                    }
                    break;
                case 2:
                    if (!selectedPackages.contains(new BeveragePackage())) {
                        packages.add(new BeveragePackage());
                        selectedPackages.add(new BeveragePackage());
                    } else {
                        System.out.println("Package already selected. Please choose another package.");
                    }
                    break;
                case 3:
                    if (!selectedPackages.contains(new WifiPackage())) {
                        packages.add(new WifiPackage());
                        selectedPackages.add(new WifiPackage());
                    } else {
                        System.out.println("Package already selected. Please choose another package.");
                    }
                    break;
                default:
                    break;
            }
        } while (option != 0);

        return packages;
    }

    public CruiseRoom selectRoom() {
        int roomIndex;
        do {
            displayRoomTypes();
            roomIndex = getUserInput("Select a room type (select the number associated with the room type): ", CruiseRoom.getRoomTypes().size()) - 1;
            if (roomIndex < 0) {
                System.out.println("Invalid selection. Please select a valid room type.");
            }
        } while (roomIndex < 0);

        String roomType = CruiseRoom.getRoomTypes().get(roomIndex);

        int roomNumber;
        do {
            roomNumber = getUserInput("Enter the room number (1-100): ", 100);
            if (roomNumber <= 0 || roomNumber > 100) {
                System.out.println("Invalid room number. Please enter a number between 1 and 100.");
            }
        } while (roomNumber <= 0 || roomNumber > 100);

        return new CruiseRoomFactory().createCruiseRoom(roomType, roomNumber);
    }

    private void displayCruiseShips() {
        int parentCompanyIndex = 0;
        for (int i = 0; i < cruiseShips.size(); i++) {
            String[] shipData = cruiseShips.get(i);
            System.out.println((i + 1) + ". " + shipData[parentCompanyIndex]);
        }
    }

    public void displayRoomTypes() {
        DecimalFormat df = new DecimalFormat("#.00");
        List<String> roomTypes = CruiseRoom.getRoomTypes();
        System.out.println("Available room types:");
        for (int i = 0; i < roomTypes.size(); i++) {
            String roomType = roomTypes.get(i);
            double cost = CruiseRoom.getCostForRoomType(roomType);
            System.out.println((i + 1) + ". " + roomType + " - $" + df.format(cost));
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

