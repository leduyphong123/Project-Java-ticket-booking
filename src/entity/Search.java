package entity;

import java.util.List;

public interface Search {
    List<FlightDetails> searchList(List<FlightDetails> flightDetailsList,String type);
}
