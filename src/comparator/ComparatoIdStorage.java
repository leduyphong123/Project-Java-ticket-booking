package comparator;

import entity.Storage;

import java.util.Comparator;

public class ComparatoIdStorage implements Comparator<Storage> {
    @Override
    public int compare(Storage s1, Storage s2) {
        return s1.getId()-s2.getId();
    }
}
