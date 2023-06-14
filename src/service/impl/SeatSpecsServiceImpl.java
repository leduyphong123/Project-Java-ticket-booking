package service.impl;

import constType.ConstTypeProject;
import entity.ChairDetails;
import entity.ChairPrice;
import entity.SeatSpecs;
import service.FileHandleService;
import service.SeatSpecsService;
import service.builder.SeatSpecsBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
            if (temp.getType().equals(ConstTypeProject.TYPE_SKY_BOSS)){
                newSeatSpecsAndSave(idFlightDetail, priceSkyBoss, temp);
            }else   if (temp.getType().equals(ConstTypeProject.TYPE_DELUXE)){
                newSeatSpecsAndSave(idFlightDetail, priceDeluxe, temp);
            }else{
                newSeatSpecsAndSave(idFlightDetail, priceOriginal, temp);
            }
        }
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
