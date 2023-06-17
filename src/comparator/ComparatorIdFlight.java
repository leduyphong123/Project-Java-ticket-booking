package comparator;

import entity.Flight;

import java.util.Comparator;

public class ComparatorIdFlight implements Comparator<Flight> {
    @Override
    public int compare(Flight f1, Flight f2) {
        if (f1.getId()>f2.getId()){
            return 1;
        }else if (f1.getId()<f2.getId()){
            return -1;
        }else {
            return 0;
        }
    }
}
