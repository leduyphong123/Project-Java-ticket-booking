package entity.impl;

import entity.Flight;
import entity.FlightDetails;
import entity.Search;
import service.FlightService;
import service.impl.FlightServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchDateFlightDetailsImpl implements Search {

    @Override
    public List<FlightDetails> searchFlightDetailsList(List<FlightDetails> flightDetailsList, String type) {
        List<FlightDetails> detailsList = new ArrayList<>();
        for (FlightDetails element:flightDetailsList){
            if (element.getDate().equals(type)){
                detailsList.add(element);
            }
        }
        return detailsList;
    }
}
