package service.impl;

import constType.ConstTypeProject;
import entity.Chair;
import entity.ChairDetails;
import entity.Flight;
import service.IdDefaultHandle;
import service.builder.ChairDetailsBuilder;
import service.ChairDetailsService;
import service.FileHandleService;
import service.builder.FlightBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChairDetailsServiceImpl implements ChairDetailsService {
    private int index = 1;

    @Override
    public boolean saveChairDetails(ChairDetails chairDetails, int flightId) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_CHAIR_DETAILS
                        + flightId
                        + ConstTypeProject.CSV)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(
                    ConstTypeProject.PATH_CHAIR_DETAILS
                            + flightId
                            + ConstTypeProject.CSV, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(chairDetails.getId()));
            bw.write(",");
            bw.write(String.valueOf(chairDetails.getIdChair()));
            bw.write(",");
            bw.write(chairDetails.getChairName());
            bw.write(",");
            bw.write(chairDetails.getType());
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
    public boolean saveListChairDetails(Chair chair, String nameLine, int numberChair, String type) {
        if (numberChair == 0) {
            return true;
        }
        switch (type) {
            case ConstTypeProject.TYPE_SKY_BOSS:
                saveType(chair, nameLine, numberChair, ConstTypeProject.TYPE_SKY_BOSS);
                break;
            case ConstTypeProject.TYPE_DELUXE:
                saveType(chair, nameLine, numberChair, ConstTypeProject.TYPE_DELUXE);
                break;
            case ConstTypeProject.TYPE_ORIGINAL:
                saveType(chair, nameLine, numberChair, ConstTypeProject.TYPE_ORIGINAL);
                break;
            default:
                return false;
        }
        index += numberChair;
        return true;
    }

    private void saveType(Chair chair, String nameLine, int numberChair, String type) {
        for (int i = index; i < numberChair + index; i++) {
            int flightId = chair.getIdFlight();
            ChairDetails chairDetails = new ChairDetailsBuilder()
                    .withIdBuilder(getChairDetailsId(flightId))
                    .withIdChairBuilder(chair.getId())
                    .withChairNameBuilder(nameLine + i)
                    .withTypeBuilder(type)
                    .builder();
            saveChairDetails(chairDetails, flightId);
            IdDefaultHandle.writeIdDefault(getChairDetailsId(flightId)+1,
                    ConstTypeProject.PATH_CHAIR_DETAILS_ID
                    + flightId
                    + ConstTypeProject.CSV);
        }
    }

    public int getChairDetailsId(int flightId) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR_DETAILS_ID
                + flightId
                + ConstTypeProject.CSV)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_CHAIR_DETAILS_ID
                + flightId
                + ConstTypeProject.CSV).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_CHAIR_DETAILS_ID
                    + flightId
                    + ConstTypeProject.CSV);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_CHAIR_DETAILS_ID
                + flightId
                + ConstTypeProject.CSV);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }
    @Override
    public void setIndexDefault() {
        this.index = 1;
    }

    @Override
    public List<ChairDetails> getAllByFlightId(int idFlight) {
        if (!FileHandleService.isFileEmtry(
                ConstTypeProject.PATH_CHAIR_DETAILS
                        + idFlight
                        + ConstTypeProject.CSV)) {
            return null;
        }
        List<ChairDetails> chairDetailsList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(
                    ConstTypeProject.PATH_CHAIR_DETAILS
                            + idFlight
                            + ConstTypeProject.CSV);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                ChairDetails chairDetails = new ChairDetailsBuilder()
                        .withIdBuilder(Integer.valueOf(result[0]))
                        .withIdChairBuilder(Integer.valueOf(result[1]))
                        .withChairNameBuilder(result[2])
                        .withTypeBuilder(result[3])
                        .builder();
                chairDetailsList.add(chairDetails);
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
        return chairDetailsList;
    }

    @Override
    public void deleteFile(int idFlight) {
        FileHandleService.deleteFile(ConstTypeProject.PATH_CHAIR_DETAILS
                + idFlight
                + ConstTypeProject.CSV);
        FileHandleService.deleteFile(ConstTypeProject.PATH_CHAIR_DETAILS_ID
                + idFlight
                + ConstTypeProject.CSV);

    }

}
