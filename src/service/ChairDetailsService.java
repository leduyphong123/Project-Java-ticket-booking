package service;

import entity.Chair;
import entity.ChairDetails;

import java.util.List;

public interface ChairDetailsService {
    boolean saveChairDetails(ChairDetails chairDetails,int flightId);

    boolean saveListChairDetails(Chair chair,String nameLine, int numberChair, String type);
    void setIndexDefault();

    List<ChairDetails> getAllByFlightId(int idFlight);
}
