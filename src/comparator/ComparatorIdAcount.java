package comparator;

import entity.Acount;
import entity.Storage;

import java.util.Comparator;

public class ComparatorIdAcount implements Comparator<Acount> {
    @Override
    public int compare(Acount a1, Acount a2) {
        if (a1.getId()>a2.getId()){
            return 1;
        }else if (a1.getId()<a2.getId()){
            return -1;
        }else {
            return 0;
        }
    }
}