package service;

import entity.Flight;

import java.util.List;

public interface FlightService {
    boolean saveFlight(Flight flight);
    List<Flight> getAllFlight();
    int getFlightId();
}
