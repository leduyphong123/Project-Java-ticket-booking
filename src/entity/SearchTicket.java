package entity;

import java.util.List;

public interface SearchTicket {
    List<Ticket> searchList(List<Ticket> ticketList, String type);
}
