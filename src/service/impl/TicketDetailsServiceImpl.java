package service.impl;

import constType.ConstTypeProject;
import entity.TiketDetails;
import service.FileHandleService;
import service.TicketDetailService;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class TicketDetailsServiceImpl implements TicketDetailService {
    @Override
    public boolean saveTicketDetail(TiketDetails tiketDetails) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_TICKET_DETAILS)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_TICKET_DETAILS, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(tiketDetails.getTicketId()));
            bw.write(",");
            bw.write(tiketDetails.getEmail());
            bw.write(",");
            bw.write(tiketDetails.getTitle());
            bw.write(",");
            bw.write(tiketDetails.getLastName());
            bw.write(",");
            bw.write(tiketDetails.getFirtName());
            bw.write(",");
            bw.write(tiketDetails.getDateOfBirth());
            bw.write(",");
            bw.write(tiketDetails.getNationality());
            bw.write(",");
            bw.write(tiketDetails.getPayment());
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
}
