package cruiseRooms;

public class InsideCabin extends CruiseRoom{
    public InsideCabin(final int roomNumber) {
        super("I", roomNumber, 300.00);
    }
    @Override
    public String getRoomType() {
        return "Inside Cabin";
    }
}
