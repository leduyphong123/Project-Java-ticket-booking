package comparator;

import entity.Flight;

import java.util.Comparator;

public class ComparatorIdFlight implements Comparator<Flight> {
    @Override
    public int compare(Flight f1, Flight f2) {
        return f1.getId()-f2.getId();
    }
}
