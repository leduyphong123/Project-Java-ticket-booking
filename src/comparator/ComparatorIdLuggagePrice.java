package comparator;

import entity.LuggagePrice;

import java.util.Comparator;

public class ComparatorIdLuggagePrice implements Comparator<LuggagePrice> {
    @Override
    public int compare(LuggagePrice l1, LuggagePrice l2) {
        return l1.getId()-l2.getId();
    }
}
