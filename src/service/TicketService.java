package service;

import entity.Ticket;

public interface TicketService {
    boolean saveTicket(Ticket ticket);

    int getTicketId();
}
