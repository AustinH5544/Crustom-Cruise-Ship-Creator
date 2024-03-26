package factory;

import cruiseRooms.*;

public class CruiseRoomFactory {

    public CruiseRoom createCruiseRoom(final String roomType, final int roomNumber) {
        if (roomType.equalsIgnoreCase("Inside Cabin")) {
            return new InsideCabin(roomNumber);
        } else if (roomType.equalsIgnoreCase("Cabin with Window")) {
            return new CabinWithWindow(roomNumber);
        } else if (roomType.equalsIgnoreCase("Cabin with Balcony")) {
            return new CabinWithBalcony(roomNumber);
        } else if (roomType.equalsIgnoreCase("Cabin Suite")) {
            return new CabinSuite(roomNumber);
        } else {
            throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }
}
