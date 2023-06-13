package service.impl;

import constType.ConstTypeProject;
import entity.ChairPrice;
import entity.Flight;
import service.ChairPriceService;
import service.FileHandleService;
import service.builder.ChairPriceBuilder;
import service.builder.FlightBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ChairPriceServiceImpl implements ChairPriceService {
    @Override
    public boolean saveChairPrice(ChairPrice chairPrice, int idFlightDetail) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR_PRICE_DEFAULT +
                "_" + idFlightDetail + ConstTypeProject.CSV)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_CHAIR_PRICE_DEFAULT +
                    "_" + idFlightDetail + ConstTypeProject.CSV, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(chairPrice.getId()));
            bw.write(",");
            bw.write(chairPrice.getType());
            bw.write(",");
            bw.write(String.valueOf(chairPrice.getPrice()));
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
    public List<ChairPrice> getChairPriceList(int idFlightDetail) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DEFAULT +
                "_" + idFlightDetail + ConstTypeProject.CSV)) {
            return null;
        }
        List<ChairPrice> chairPriceList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_FLIGHT_DEFAULT +
                    "_" + idFlightDetail + ConstTypeProject.CSV);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                ChairPrice chairPrice = new ChairPriceBuilder()
                        .withIdBuilder(Integer.valueOf(result[0]))
                        .withTypeBuilder(result[1])
                        .withPriceBuilder(Long.valueOf(result[2]))
                        .builder();
                chairPriceList.add(chairPrice);
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
        return chairPriceList;
    }
}
