package service;

import entity.Acount;

import java.util.List;

public interface AcountService {
    boolean saveAcount(Acount acount);

    int getAcountId();

    boolean checkEmail(String email);

    boolean checkAcount(String email, String passworld);

    Acount getUserNow(String email, String passworld);


    boolean deleteAcountId(int acountId);

    List<Acount> getAllAcount();

    boolean newPassworld(Acount acountSession);
}
