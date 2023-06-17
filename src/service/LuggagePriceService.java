package service;

import entity.LuggagePrice;

import java.util.List;

public interface LuggagePriceService {
    boolean saveLuggagePrice(LuggagePrice luggagePrice, String airlineName);

    int getLuggagePriceId(String airlineName);

    List<LuggagePrice> getAirlineNameList(String airlineName);


    LuggagePrice getLPFromLPId(String airlineName, String luggagePriceId);

    List<LuggagePrice> getAll(String arilineName);

    boolean edit(String arilineName, String name, String valume, String price,String luggagePriceId);

    boolean delete(String arilineName, String luggagePriceId);
}
