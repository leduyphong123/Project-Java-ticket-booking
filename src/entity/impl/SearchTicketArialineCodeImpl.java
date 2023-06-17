package entity.impl;

import entity.SearchTicket;
import entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SearchTicketArialineCodeImpl implements SearchTicket {
    @Override
    public List<Ticket> searchList(List<Ticket> ticketList, String type) {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket element:ticketList){
            if (element.getAirlineCode().equals(type)){
                tickets.add(element);
            }
        }
        return tickets;
    }
}
