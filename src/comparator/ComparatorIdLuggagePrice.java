package comparator;

import entity.LuggagePrice;

import java.util.Comparator;

public class ComparatorIdLuggagePrice implements Comparator<LuggagePrice> {
    @Override
    public int compare(LuggagePrice l1, LuggagePrice l2) {
        if (l1.getId()>l1.getId()){
            return 1;
        }else if (l1.getId()<l2.getId()){
            return -1;
        }else {
            return 0;
        }
    }
}
