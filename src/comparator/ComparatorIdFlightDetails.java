package comparator;

import entity.FlightDetails;

import java.util.Comparator;

public class ComparatorIdFlightDetails implements Comparator<FlightDetails> {
    @Override
    public int compare(FlightDetails f1, FlightDetails f2) {
        return f1.getId()-f2.getId();
    }
}
