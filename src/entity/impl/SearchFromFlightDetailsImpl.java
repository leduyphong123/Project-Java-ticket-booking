package entity.impl;

import entity.Flight;
import entity.FlightDetails;
import entity.Search;
import service.FlightService;
import service.impl.FlightServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchFromFlightDetailsImpl implements Search {

    @Override
    public List<FlightDetails> searchList(List<FlightDetails> flightDetailsList, String type) {
        List<FlightDetails> detailsList = new ArrayList<>();
        FlightService flightService =new FlightServiceImpl();
        for (FlightDetails element:flightDetailsList){
            Flight flight = flightService.getFlightToId(element.getIdFlight());
            if (flight.getFrom_location().equals(type)){
                detailsList.add(element);
            }
        }
        return detailsList;
    }
}
