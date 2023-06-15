package service.impl;

import constType.ConstTypeProject;
import entity.Flight;
import entity.LuggagePrice;
import service.FileHandleService;
import service.IdDefaultHandle;
import service.LuggagePriceService;
import service.builder.FlightBuilder;
import service.builder.LuggagePriceBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LuggagePriceServiceImpl implements LuggagePriceService {
    @Override
    public boolean saveLuggagePrice(LuggagePrice luggagePrice, String airlineName) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_LUGGAGE
                + airlineName
                + ConstTypeProject.CSV
        )) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(
                    ConstTypeProject.PATH_LUGGAGE
                    + airlineName
                    + ConstTypeProject.CSV, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(luggagePrice.getId()));
            bw.write(",");
            bw.write(luggagePrice.getName());
            bw.write(",");
            bw.write(String.valueOf(luggagePrice.getValume()));
            bw.write(",");
            bw.write(String.valueOf(luggagePrice.getPrice()));
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
    public int getLuggagePriceId(String airlineName) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_LUGGAGE_ID
                        + airlineName
                        + ConstTypeProject.CSV
        )) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(
                ConstTypeProject.PATH_LUGGAGE_ID
                        + airlineName
                        + ConstTypeProject.CSV
        ).size()==0){
            IdDefaultHandle.writeIdDefault(1,
                    ConstTypeProject.PATH_LUGGAGE_ID
                    + airlineName
                    + ConstTypeProject.CSV);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(
                ConstTypeProject.PATH_LUGGAGE_ID
                        + airlineName
                        + ConstTypeProject.CSV
        );
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

    @Override
    public List<LuggagePrice> getAirlineNameList(String airlineName) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_LUGGAGE
                + airlineName
                + ConstTypeProject.CSV)) {
            return null;
        }
        List<LuggagePrice> luggagePriceList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(
                    ConstTypeProject.PATH_LUGGAGE
                            + airlineName
                            + ConstTypeProject.CSV
            );
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                LuggagePrice luggagePrice = new LuggagePriceBuilder()
                        .withIdBuilder(Integer.parseInt(result[0]))
                        .withNameBuilder(result[1])
                        .withValumeBuilder(Long.parseLong(result[2]))
                        .withPriceBuilder(Long.parseLong(result[3]))
                        .builder();
                luggagePriceList.add(luggagePrice);
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
        return luggagePriceList;
    }

    @Override
    public LuggagePrice getLPFromLPId(String airlineName, String luggagePriceId) {
        List<LuggagePrice> luggagePriceList = getAirlineNameList(airlineName);
        for (LuggagePrice element:luggagePriceList){
            if (element.getId()==Integer.parseInt(luggagePriceId)){
                return element;
            }
        }
        return null;
    }


}
