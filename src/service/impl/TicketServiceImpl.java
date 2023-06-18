package service.impl;

import comparator.ComparatorIdTicket;
import constType.ConstTypeProject;
import entity.Ticket;
import service.FileHandleService;
import service.IdDefaultHandle;
import service.TicketService;
import service.builder.TicketBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
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
            bw.write(ticket.getTitle());
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
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_TICKET_ID).size() == 0) {
            IdDefaultHandle.writeIdDefault(1, ConstTypeProject.PATH_TICKET_ID);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_TICKET_ID);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

    @Override
    public List<Ticket> getAll() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_TICKET)) {
            return null;
        }
        List<Ticket> ticketList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_TICKET);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                Ticket ticket = new TicketBuilder()
                        .withId(Integer.parseInt(result[0]))
                        .withUserId(Integer.parseInt(result[1]))
                        .withTitle(result[2])
                        .withFullName(result[3])
                        .withValume(Long.parseLong(result[4]))
                        .withAirlineCode(result[5])
                        .withDepartureTime(result[6])
                        .withAirlineTime(result[7])
                        .withStatus(Boolean.parseBoolean(result[8]))
                        .builder();
                ticketList.add(ticket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    return null;
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return ticketList;
    }

    @Override
    public boolean isExit(int ticketId) {
        List<Ticket> ticketList = getAll();
        for (Ticket element : ticketList) {
            if (element.getId() == ticketId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIn(int ticketId) {
        List<Ticket> ticketList = getAll();
        if (!isExit(ticketId)) {
            return false;
        }
        int index = -1;
        Ticket tempTicket = null;
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getId() == ticketId) {
                index = i;
                tempTicket = ticketList.get(i);
                break;
            }
        }
        ticketList.remove(index);
        tempTicket.setStatus(true);
        ticketList.add(tempTicket);
        ComparatorIdTicket comparatorIdTicket = new ComparatorIdTicket();
        Collections.sort(ticketList, comparatorIdTicket);
        return saveTicketList(ticketList);
    }

    @Override
    public List<Ticket> getTicketUser(int userId) {
        List<Ticket> ticketList = getAll();
        List<Ticket> temp = new ArrayList<>();
        for (Ticket element : ticketList) {
            if (element.getUserId() == userId) {
                temp.add(element);
            }
        }
        return temp;
    }

    private boolean saveTicketList(List<Ticket> ticketList) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_TICKET)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_TICKET, false);
            bw = new BufferedWriter(fw);
            for (Ticket element : ticketList) {
                bw.write(String.valueOf(element.getId()));
                bw.write(",");
                bw.write(String.valueOf(element.getUserId()));
                bw.write(",");
                bw.write(element.getTitle());
                bw.write(",");
                bw.write(element.getFullName());
                bw.write(",");
                bw.write(String.valueOf(element.getValume()));
                bw.write(",");
                bw.write(element.getAirlineCode());
                bw.write(",");
                bw.write(element.getDepartureTime());
                bw.write(",");
                bw.write(element.getAirlineTime());
                bw.write(",");
                bw.write(String.valueOf(element.getStatus()));
                bw.newLine();
            }

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
}
