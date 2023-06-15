package service;

import entity.Acount;

public interface AcountService {
    boolean saveAcount(Acount acount);

    int getAcountId();

    boolean checkEmail(String email);

    boolean checkAcount(String email, String passworld);

    Acount getUserNow(String email, String passworld);
}
