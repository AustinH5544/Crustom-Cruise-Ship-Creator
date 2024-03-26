package cruiseRooms;

public class CabinSuite extends CruiseRoom{
    public CabinSuite(final int roomNumber) {
        super("S", roomNumber, 700.00);
    }
    @Override
    public String getRoomType() {
        return "Cabin Suite";
    }
}
