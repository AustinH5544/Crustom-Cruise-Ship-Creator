package cruiseRooms;

public class CabinWithBalcony extends CruiseRoom {

    public CabinWithBalcony(final int roomNumber) {
        super("B", roomNumber, 550.00);
    }

    @Override
    public String getRoomType() {
        return "Cabin with Balcony";
    }
}
