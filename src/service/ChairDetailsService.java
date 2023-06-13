package service;

import entity.Chair;
import entity.ChairDetails;

public interface ChairDetailsService {
    boolean saveChairDetails(ChairDetails chairDetails,int flightId);

    boolean saveListChairDetails(Chair chair,String nameLine, int numberChair, String type);
    void setIndexDefault();
}
