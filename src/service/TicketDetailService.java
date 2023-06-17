package service;

import entity.TiketDetails;

import java.util.List;

public interface TicketDetailService {
    boolean saveTicketDetail(TiketDetails tiketDetails);


    TiketDetails getTicketDetailsUser(int ticketId);
}
