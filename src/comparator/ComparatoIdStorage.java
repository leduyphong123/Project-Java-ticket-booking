package comparator;

import entity.Flight;
import entity.Storage;

import java.util.Comparator;

public class ComparatoIdStorage implements Comparator<Storage> {
    @Override
    public int compare(Storage s1, Storage s2) {
        if (s1.getId()>s2.getId()){
            return 1;
        }else if (s1.getId()<s2.getId()){
            return -1;
        }else {
            return 0;
        }
    }
}
