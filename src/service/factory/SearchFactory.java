package service.factory;

import constType.ConstTypeProject;
import entity.Search;
import entity.impl.SearchDateFlightDetailsImpl;
import entity.impl.SearchFromFlightDetailsImpl;
import entity.impl.SearchToFlightDetailsImpl;

public class SearchFactory {
    public static Search getSearchFatory(String type) {
        if (type.equals(ConstTypeProject.TYPE_DATE)) {
            return new SearchDateFlightDetailsImpl();
        } else if (type.equals(ConstTypeProject.TYPE_FORM)) {
            return new SearchFromFlightDetailsImpl();
        } else if (type.equals(ConstTypeProject.TYPE_TO)) {
            return new SearchToFlightDetailsImpl();
        }
        return null;
    }
}
