package cruiseModel;

import cruiseRooms.CruiseRoom;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

public class Client implements PropertyChangeListener {

    private String name;

    public Client(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void remove(final CruiseRoom room){
        room.removePropertyChangeListener(this);
        System.out.println(this.name + " was removed from the subscriber program\n");
    }

    public void add(final CruiseRoom room){
        room.addPropertyChangeListener(this);
        System.out.println(this.name + " was added to the subscriber program\n");
    }

    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        DecimalFormat df = new DecimalFormat("#.00");
        double oldCost = Double.parseDouble(event.getOldValue().toString());
        double newCost = Double.parseDouble(event.getNewValue().toString());
        double savings = oldCost - newCost;

        System.out.println(this.name + " - The price of your room has decreased: "  +
                " [old cost -> $" + df.format(oldCost) + "] | [new cost -> $"
                + df.format(newCost) + "]\n"
                + "We will process your refund immediately."
                + "Your refund amount is $" + df.format(savings) + "\n");
    }

}
