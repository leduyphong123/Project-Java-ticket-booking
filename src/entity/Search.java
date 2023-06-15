package entity;

import java.util.List;

public interface Search {
    List<FlightDetails> searchFlightDetailsList(List<FlightDetails> flightDetailsList,String type);
}
