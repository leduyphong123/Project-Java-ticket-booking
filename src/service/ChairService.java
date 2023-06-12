package service;

import entity.Chair;

public interface ChairService {
    boolean saveChair(Chair chair);


    Chair getChair();

    int getChairId();
}
