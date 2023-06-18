package comparator;

import entity.Acount;

import java.util.Comparator;

public class ComparatorIdAcount implements Comparator<Acount> {
    @Override
    public int compare(Acount a1, Acount a2) {
        return a1.getId()-a2.getId();
    }
}
