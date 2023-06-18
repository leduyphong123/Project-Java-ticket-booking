package comparator;

import entity.FlightDetails;

import java.util.Comparator;
import java.util.Map;

public class ComparotorPrice implements Comparator<Map.Entry<FlightDetails,Long>> {

    @Override
    public int compare(Map.Entry<FlightDetails, Long> m1, Map.Entry<FlightDetails, Long> m2) {
        return m1.getValue().compareTo(m2.getValue());
    }
}
