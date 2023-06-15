package service;

import entity.ChairDetails;
import entity.ChairPrice;
import entity.SeatSpecs;

import java.util.List;

public interface SeatSpecsService {
    boolean saveSeatSpecs(SeatSpecs seatSpecs, int idFlightDetails);

    void saveSeatSpecsList(List<ChairDetails> chairDetailsList, List<ChairPrice> chairPriceList,int idFlightDetail);


    List<SeatSpecs> getTypeAndId(int idFlightDetail, ChairPrice chairPrice);

    boolean editFile(SeatSpecs seatSpecs, String flightDetailsId);
}
