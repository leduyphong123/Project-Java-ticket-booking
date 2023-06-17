package service.impl;

import comparator.ComparatorIdFlightDetails;
import constType.ConstTypeProject;
import entity.FlightDetails;
import service.FileHandleService;
import service.FlightDetailsService;
import service.IdDefaultHandle;
import service.builder.FlightDetailsBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightDetailsServiceImpl implements FlightDetailsService {
    @Override
    public boolean saveFlightDetails(FlightDetails flightDetails) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DETAILS)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_FLIGHT_DETAILS, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(flightDetails.getId()));
            bw.write(",");
            bw.write(String.valueOf(flightDetails.getIdFlight()));
            bw.write(",");
            bw.write(flightDetails.getDate());
            bw.write(",");
            bw.write(String.valueOf(flightDetails.getStorageValume()));
            bw.write(",");
            bw.write(String.valueOf(flightDetails.getUsedStorageValume()));
            bw.write(",");
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
    public int getFlightDetailsId() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DETAILS_ID)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_FLIGHT_DETAILS_ID).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_FLIGHT_DETAILS_ID);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_FLIGHT_DETAILS_ID);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

    @Override
    public List<FlightDetails> getAllList() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DETAILS)) {
            return null;
        }
        List<FlightDetails> flightDetailsList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_FLIGHT_DETAILS);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                FlightDetails flightDetails = new FlightDetailsBuilder()
                        .withId(Integer.parseInt(result[0]))
                        .withIdFlight(Integer.parseInt(result[1]))
                        .withDate(result[2])
                        .withStorageValume(Long.parseLong(result[3]))
                        .withUsedStorageValume(Long.parseLong(result[4]))
                        .builder();
                flightDetailsList.add(flightDetails);
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
        return flightDetailsList;
    }

    @Override
    public int getFlightId(String flightDetailsId) {
        List<FlightDetails> flightDetailsList=getAllList();
        for (FlightDetails element:flightDetailsList){
            if (element.getIdFlight()==Integer.parseInt(flightDetailsId)){
                return element.getIdFlight();
            }
        }
        return -1;
    }

    @Override
    public boolean isIdExit(int flightDetailsId) {
        List<FlightDetails> flightDetailsList = getAllList();
        for(FlightDetails element:flightDetailsList){
            if (element.getId()==flightDetailsId){
                return true;
            }
        }
        return false;
    }

    private FlightDetails getFlightDetailsToId(int flightDetailsId) {
        List<FlightDetails> flightDetailsList = getAllList();
        for (FlightDetails element : flightDetailsList){
            if (element.getId()==flightDetailsId){
                return element;
            }
        }
        return null;
    }

    @Override
    public boolean editFlightDetails(int flightDetailsId, String date, long usedStorageValume) {
        List<FlightDetails> flightDetailsList = getAllList();
        FlightDetails flightDetails = getFlightDetailsToId(flightDetailsId);
        int index=-1;
        for (int i=0;i<flightDetailsList.size();i++){
            if (flightDetailsList.get(i).getId()==flightDetailsId){
                index = i;
                break;
            }
        }
        FlightDetails flightDetailsNew = new FlightDetailsBuilder()
                .withId(flightDetails.getId())
                .withIdFlight(flightDetails.getIdFlight())
                .withDate(date)
                .withStorageValume(flightDetails.getStorageValume())
                .withUsedStorageValume(usedStorageValume)
                .builder();
        flightDetailsList.remove(index);
        flightDetailsList.add(flightDetailsNew);
        ComparatorIdFlightDetails comparatorIdFlightDetails = new ComparatorIdFlightDetails();
        Collections.sort(flightDetailsList,comparatorIdFlightDetails);
        return saveFlightDetailsList(flightDetailsList);
    }

    @Override
    public boolean deleteFlightDetails(int flightDetailsId) {
        List<FlightDetails> flightDetailsList = getAllList();
        int index=-1;
        for (int i=0;i<flightDetailsList.size();i++){
            if (flightDetailsList.get(i).getId()==flightDetailsId){
                index = i;
                break;
            }
        }
        flightDetailsList.remove(index);
        ComparatorIdFlightDetails comparatorIdFlightDetails = new ComparatorIdFlightDetails();
        Collections.sort(flightDetailsList,comparatorIdFlightDetails);
        return saveFlightDetailsList(flightDetailsList);
    }

    @Override
    public boolean isUsedStorageMax(int flightDetailsId, String usedStorageValume) {
        List<FlightDetails> flightDetailsList = getAllList();
        for (FlightDetails element :flightDetailsList){
            if (element.getIdFlight()==flightDetailsId){
                if (element.getStorageValume()>=Long.parseLong(usedStorageValume)){
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean newUsedStorageValume(String flightDetailsId,long valume) {
        List<FlightDetails> flightDetailsList = getAllList();
        FlightDetails temp=null ;
        int index=-1;
        for (int i=0;i<flightDetailsList.size();i++){
            if (flightDetailsList.get(i).getId()==Integer.parseInt(flightDetailsId)){
                index = i;
                temp=flightDetailsList.get(i);
                break;
            }
        }
        FlightDetails flightDetailsNew = new FlightDetailsBuilder()
                .withId(temp.getId())
                .withIdFlight(temp.getIdFlight())
                .withDate(temp.getDate())
                .withStorageValume(temp.getStorageValume())
                .withUsedStorageValume(temp.getUsedStorageValume()+valume)
                .builder();
        flightDetailsList.remove(index);
        flightDetailsList.add(flightDetailsNew);
        ComparatorIdFlightDetails comparatorIdFlightDetails = new ComparatorIdFlightDetails();
        Collections.sort(flightDetailsList,comparatorIdFlightDetails);
        return saveFlightDetailsList(flightDetailsList);
    }

    private boolean saveFlightDetailsList(List<FlightDetails> flightDetailsList) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DETAILS)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_FLIGHT_DETAILS, false);
            bw = new BufferedWriter(fw);
            for (FlightDetails element : flightDetailsList){
                bw.write(String.valueOf(element.getId()));
                bw.write(",");
                bw.write(String.valueOf(element.getIdFlight()));
                bw.write(",");
                bw.write(element.getDate());
                bw.write(",");
                bw.write(String.valueOf(element.getStorageValume()));
                bw.write(",");
                bw.write(String.valueOf(element.getUsedStorageValume()));
                bw.write(",");
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
