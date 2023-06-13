package service;

import entity.FlightDetails;

public interface FlightDetailsService {
    boolean saveFlightDetails(FlightDetails flightDetails);

    int getFlightDetailsId();
}
