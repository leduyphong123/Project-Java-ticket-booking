package service;

import entity.FlightDetails;

import java.util.List;

public interface FlightDetailsService {
    boolean saveFlightDetails(FlightDetails flightDetails);

    int getFlightDetailsId();

    List<FlightDetails> getAllList();

    int getFlightId(String flightDetailsId);
}
