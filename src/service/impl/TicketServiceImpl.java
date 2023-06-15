package service.impl;

import constType.ConstTypeProject;
import entity.Ticket;
import service.FileHandleService;
import service.IdDefaultHandle;
import service.TicketService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    @Override
    public boolean saveTicket(Ticket ticket) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_TICKET)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_TICKET, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(ticket.getId()));
            bw.write(",");
            bw.write(String.valueOf(ticket.getUserId()));
            bw.write(",");
            bw.write(ticket.getFullName());
            bw.write(",");
            bw.write(String.valueOf(ticket.getValume()));
            bw.write(",");
            bw.write(ticket.getAirlineCode());
            bw.write(",");
            bw.write(ticket.getDepartureTime());
            bw.write(",");
            bw.write(ticket.getAirlineTime());
            bw.write(",");
            bw.write(String.valueOf(ticket.getStatus()));
            bw.newLine();
        } catch (Exception e) {
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    return false;
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getTicketId() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_TICKET_ID)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_TICKET_ID).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_TICKET_ID);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_TICKET_ID);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }
}
