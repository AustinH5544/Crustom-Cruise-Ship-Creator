package cruiseRooms;

public class CabinWithWindow extends CruiseRoom{
    public CabinWithWindow(final int roomNumber) {
        super("W", roomNumber, 450.00);
    }
    @Override
    public String getRoomType() {
        return "Cabin with Window";
    }
}
