package service;

import entity.FlightDetails;

import java.util.List;

public interface FlightDetailsService {
    boolean saveFlightDetails(FlightDetails flightDetails);

    int getFlightDetailsId();

    List<FlightDetails> getAllList();

    int getFlightId(String flightDetailsId);

    boolean isIdExit(int flightDetailsId);



    boolean editFlightDetails(int flightDetailsId, String date, long usedStorageValume);

    boolean deleteFlightDetails(int flightDetailsId);

    boolean isUsedStorageMax(int flightDetailsId, String usedStorageValume);


    boolean newUsedStorageValume(String flightDetailsId, long valume);

}
