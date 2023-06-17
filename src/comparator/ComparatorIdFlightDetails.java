package comparator;

import entity.Flight;
import entity.FlightDetails;

import java.util.Comparator;

public class ComparatorIdFlightDetails implements Comparator<FlightDetails> {
    @Override
    public int compare(FlightDetails f1, FlightDetails f2) {
        if (f1.getId()>f2.getId()){
            return 1;
        }else if (f1.getId()<f2.getId()){
            return -1;
        }else {
            return 0;
        }
    }
}
