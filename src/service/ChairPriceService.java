package service;

import entity.ChairPrice;

import java.util.List;

public interface ChairPriceService {
    boolean saveChairPrice(ChairPrice chairPrice,int idFlightDetail);


    List<ChairPrice> getAllByFlightDetailId(int idFlightDetail);

}
