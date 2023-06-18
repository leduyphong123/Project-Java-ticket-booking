package comparator;

import entity.Chair;

import java.util.Comparator;

public class ComparatorIdChair implements Comparator<Chair> {
    @Override
    public int compare(Chair c1, Chair c2) {
        return c1.getId()-c2.getId();
    }
}
