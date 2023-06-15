package service.impl;

import constType.ConstTypeProject;
import entity.ChairDetails;
import entity.ChairPrice;
import entity.Flight;
import entity.SeatSpecs;
import service.FileHandleService;
import service.SeatSpecsService;
import service.builder.FlightBuilder;
import service.builder.SeatSpecsBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SeatSpecsServiceImpl implements SeatSpecsService {
    @Override
    public boolean saveSeatSpecs(SeatSpecs seatSpecs, int idFlightDetails) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_SEAT_SPECS
                        + idFlightDetails
                        + ConstTypeProject.CSV)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(
                    ConstTypeProject.PATH_SEAT_SPECS
                            + idFlightDetails
                            + ConstTypeProject.CSV, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(seatSpecs.getId()));
            bw.write(",");
            bw.write(String.valueOf(seatSpecs.getIdChair()));
            bw.write(",");
            bw.write(seatSpecs.getChairName());
            bw.write(",");
            bw.write(seatSpecs.getType());
            bw.write(",");
            bw.write(String.valueOf(seatSpecs.getPrice()));
            bw.write(",");
            bw.write(String.valueOf(seatSpecs.isStatus()));
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
    public void saveSeatSpecsList(List<ChairDetails> chairDetailsList, List<ChairPrice> chairPriceList, int idFlightDetail) {
        long priceSkyBoss = 0;
        long priceDeluxe = 0;
        long priceOriginal = 0;
        for (int i = 0; i < chairPriceList.size(); i++) {
            ChairPrice temp = chairPriceList.get(i);
            if (temp.getType().equals(ConstTypeProject.TYPE_SKY_BOSS)) {
                priceSkyBoss = temp.getPrice();
            } else if (temp.getType().equals(ConstTypeProject.TYPE_DELUXE)) {
                priceDeluxe = temp.getPrice();
            } else {
                priceOriginal = temp.getPrice();
            }
        }
        for (int i = 0; i < chairDetailsList.size(); i++) {
            ChairDetails temp = chairDetailsList.get(i);
            if (temp.getType().equals(ConstTypeProject.TYPE_SKY_BOSS)) {
                newSeatSpecsAndSave(idFlightDetail, priceSkyBoss, temp);
            } else if (temp.getType().equals(ConstTypeProject.TYPE_DELUXE)) {
                newSeatSpecsAndSave(idFlightDetail, priceDeluxe, temp);
            } else {
                newSeatSpecsAndSave(idFlightDetail, priceOriginal, temp);
            }
        }
    }

    @Override
    public List<SeatSpecs> getTypeAndId(int idFlightDetail, ChairPrice chairPrice) {
        List<SeatSpecs> seatSpecsList = getAllSeatSpecs(idFlightDetail);
        List<SeatSpecs> resultSeatSpecsList = new ArrayList<>();
        for (SeatSpecs elment : seatSpecsList){
            if (elment.getType().equals(chairPrice.getType())){
                resultSeatSpecsList.add(elment);
            }
        }
        return resultSeatSpecsList;
    }

    @Override
    public boolean editFile(SeatSpecs seatSpecs, String flightDetailsId) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_SEAT_SPECS
                        + flightDetailsId
                        + ConstTypeProject.CSV)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        List<SeatSpecs> seatSpecsList=getAllSeatSpecs(Integer.parseInt(flightDetailsId));
        try {
            fw = new FileWriter(
                    ConstTypeProject.PATH_SEAT_SPECS
                            + flightDetailsId
                            + ConstTypeProject.CSV,false);
            bw = new BufferedWriter(fw);
            for (SeatSpecs element: seatSpecsList){
                bw.write(String.valueOf(element.getId()));
                bw.write(",");
                bw.write(String.valueOf(element.getIdChair()));
                bw.write(",");
                bw.write(element.getChairName());
                bw.write(",");
                bw.write(element.getType());
                bw.write(",");
                bw.write(String.valueOf(element.getPrice()));
                bw.write(",");
                if (element.getId()==seatSpecs.getId()){
                    bw.write(String.valueOf(false));
                }else {
                    bw.write(String.valueOf(element.isStatus()));
                }
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


    private List<SeatSpecs> getAllSeatSpecs(int idFlightDetails) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_SEAT_SPECS
                        + idFlightDetails
                        + ConstTypeProject.CSV
        )) {
            return null;
        }
        List<SeatSpecs> seatSpecsList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(
                    ConstTypeProject.PATH_SEAT_SPECS
                            + idFlightDetails
                            + ConstTypeProject.CSV
            );
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                SeatSpecs seatSpecs = new SeatSpecsBuilder()
                        .withIdBuilder(Integer.valueOf(result[0]))
                        .withIdChairBuilder(Integer.valueOf(result[1]))
                        .withChairNameBuilder(result[2])
                        .withTypeBuilder(result[3])
                        .withPriceBuilder(Long.parseLong(result[4]))
                        .withStatusBuilder(Boolean.parseBoolean(result[5]))
                        .builder();
                seatSpecsList.add(seatSpecs);
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
        return seatSpecsList;
    }

    private void newSeatSpecsAndSave(int idFlightDetail, long priceSkyBoss, ChairDetails temp) {
        SeatSpecs seatSpecs = new SeatSpecsBuilder()
                .withIdBuilder(Integer.valueOf(temp.getId()))
                .withTypeBuilder(temp.getType())
                .withChairNameBuilder(temp.getChairName())
                .withIdChairBuilder(Integer.valueOf(temp.getIdChair()))
                .withPriceBuilder(priceSkyBoss)
                .withStatusBuilder(true)
                .builder();
        saveSeatSpecs(seatSpecs, idFlightDetail);
    }


}
