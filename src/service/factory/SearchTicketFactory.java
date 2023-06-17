package service.factory;

import entity.SearchTicket;
import entity.impl.SearchTicketArialineCodeImpl;
import entity.impl.SearchTicketFullNameImpl;


public class SearchTicketFactory {
    public static SearchTicket getSearchFatory(String type) {
        if (type.equals("fullName")) {
            return new SearchTicketFullNameImpl();
        } else if (type.equals("arialineCode")) {
            return new SearchTicketArialineCodeImpl();
        }
        return null;
    }
}
