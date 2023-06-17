package service.impl;

import constType.ConstTypeProject;
import entity.TiketDetails;
import service.FileHandleService;
import service.TicketDetailService;
import service.builder.TiketDetailsBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public TiketDetails getTicketDetailsUser(int ticketId) {
        List<TiketDetails> tiketDetailsList =getAll();
        for (TiketDetails element : tiketDetailsList){
            if (element.getTicketId()==ticketId){
                return element;
            }
        }
        return null;
    }

    private List<TiketDetails> getAll() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_TICKET_DETAILS)) {
            return null;
        }
        List<TiketDetails> tiketDetailsList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_TICKET_DETAILS);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                TiketDetails tiketDetails = new TiketDetailsBuilder()
                        .withTicketId(Integer.parseInt(result[0]))
                        .withEmail(result[1])
                        .withTitle(result[2])
                        .withLastName(result[3])
                        .withFirtName(result[4])
                        .withDateOfBirth(result[5])
                        .withNationality(result[6])
                        .withPayment(result[7])
                        .builder();
                tiketDetailsList.add(tiketDetails);
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
        return tiketDetailsList;
    }
}
