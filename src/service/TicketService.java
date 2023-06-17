package service;

import entity.Ticket;

import java.util.List;

public interface TicketService {
    boolean saveTicket(Ticket ticket);

    int getTicketId();

    List<Ticket> getAll();

    boolean isExit(int ticketId);

    boolean checkIn(int ticketId);

    List<Ticket> getTicketUser(int userId);
}
