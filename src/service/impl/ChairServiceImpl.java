package service.impl;

import comparator.ComparatorIdChair;
import comparator.ComparatorIdFlight;
import constType.ConstTypeProject;
import entity.Chair;
import entity.Flight;
import service.ChairService;
import service.FileHandleService;
import service.IdDefaultHandle;
import service.builder.ChairBuilder;
import service.builder.FlightBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChairServiceImpl implements ChairService {
    private static Chair chair;
    @Override
    public boolean saveChair(Chair chair) {
        this.chair=chair;
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR)){
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_CHAIR, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(chair.getId()));
            bw.write(",");
            bw.write(String.valueOf(chair.getIdFlight()));
            bw.write(",");
            bw.write(String.valueOf(chair.getLineQuantity()));
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
    public Chair getChair() {
        return chair;
    }

    @Override
    public int getChairId() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR_ID)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_CHAIR_ID).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_CHAIR_ID);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_CHAIR_ID);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

    @Override
    public List<Chair> getAll() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR)) {
            return null;
        }
        List<Chair> chairList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_CHAIR);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                Chair chairs = new ChairBuilder()
                        .withIdBuilder(Integer.parseInt(result[0]))
                        .withIdFlightBuilder(Integer.parseInt(result[1]))
                        .withLineQuantityBuilder(Integer.parseInt(result[2]))
                        .builder();
                chairList.add(chairs);
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
        return chairList;
    }

    @Override
    public boolean isExitId(int id) {
        List<Chair> chairList = getAll();
        for (Chair element:chairList){
            if (element.getId()==id){
                return true;
            }
        }
        return false;
    }

    @Override
    public Chair getChairToFlightId(int id) {
        List<Chair> chairList = getAll();
        for (Chair element:chairList){
            if (element.getId()==id){
                return element;
            }
        }
        return null;
    }

    @Override
    public boolean editChair(Chair chair, String lineQuantity) {
        List<Chair> chairList = getAll();
        int index=-1;
        for (int i=0;i<chairList.size();i++){
            if (chairList.get(i).getId()==chair.getId()){
                index = i;
                break;
            }
        }
        Chair chairNew = new ChairBuilder()
                .withIdBuilder(chair.getId())
                .withIdFlightBuilder(chair.getIdFlight())
                .withLineQuantityBuilder(Integer.parseInt(lineQuantity))
                .builder();
        chairList.remove(index);
        chairList.add(chairNew);
        ComparatorIdChair comparatorIdChair = new ComparatorIdChair();
        Collections.sort(chairList,comparatorIdChair);
        return saveChairList(chairList);
    }

    @Override
    public void deleteChair(int flightId) {
        List<Chair> chairList = getAll();
        int index=-1;
        for (int i=0;i<chairList.size();i++){
            if (chairList.get(i).getIdFlight()==flightId){
                index = i;
                break;
            }
        }
        chairList.remove(index);
        ComparatorIdChair comparatorIdChair = new ComparatorIdChair();
        Collections.sort(chairList,comparatorIdChair);
        saveChairList(chairList);
    }

    private boolean saveChairList(List<Chair> chairList) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR)){
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_CHAIR, false);
            bw = new BufferedWriter(fw);
            for (Chair element : chairList){
                bw.write(String.valueOf(element.getId()));
                bw.write(",");
                bw.write(String.valueOf(element.getIdFlight()));
                bw.write(",");
                bw.write(String.valueOf(element.getLineQuantity()));
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
