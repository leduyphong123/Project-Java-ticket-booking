package service;

import entity.Chair;

import java.util.List;

public interface ChairService {
    boolean saveChair(Chair chair);


    Chair getChair();

    int getChairId();

    List<Chair> getAll();

    boolean isExitId(int id);

    Chair getChairToFlightId(int id);


    boolean editChair(Chair chair, String lineQuantity);

    void deleteChair(int flightId);

}
