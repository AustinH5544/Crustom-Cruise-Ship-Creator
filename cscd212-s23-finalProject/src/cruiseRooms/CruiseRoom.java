package cruiseRooms;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public abstract class CruiseRoom {

    private String level;
    private int roomNumber;
    public double cost;

    private PropertyChangeSupport pcs;

    public CruiseRoom(final String level, final int roomNumber, final double cost) {
        this.level = level;
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.pcs = new PropertyChangeSupport(this);
    }

    public void update(final double cost){
        double oldCost = this.cost;
        this.cost = cost;
        this.pcs.firePropertyChange("Cost", oldCost, cost);
    }

    public double getCost(){
        return this.cost;
    }

    public abstract String getRoomType();

    public String getLevel() {
        return this.level;
    }

    public void setLevel(final String level) {
        this.level = level;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(final int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public static List<String> getRoomTypes() {
        List<String> roomTypes = new ArrayList<>();
        roomTypes.add("Inside Cabin");
        roomTypes.add("Cabin with Window");
        roomTypes.add("Cabin with Balcony");
        roomTypes.add("Cabin Suite");
        return roomTypes;
    }

    public static double getCostForRoomType(final String roomType) {
        switch (roomType.toLowerCase()) {
            case "inside cabin":
                return 300.00;
            case "cabin with window":
                return 450.00;
            case "cabin with balcony":
                return 550.00;
            case "cabin suite":
                return 700.00;
            default:
                throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }

    public void addPropertyChangeListener(final PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener listener){
        this.pcs.removePropertyChangeListener(listener);
    }
}
