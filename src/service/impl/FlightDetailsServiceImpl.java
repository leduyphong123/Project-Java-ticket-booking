package service.impl;

import constType.ConstTypeProject;
import entity.Flight;
import entity.FlightDetails;
import service.FileHandleService;
import service.FlightDetailsService;
import service.IdDefaultHandle;
import service.builder.FlightBuilder;
import service.builder.FlightDetailsBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FlightDetailsServiceImpl implements FlightDetailsService {
    @Override
    public boolean saveFlightDetails(FlightDetails flightDetails) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DETAILS_DEFAULT)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_FLIGHT_DETAILS_DEFAULT, true);
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
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DETAILS_DEFAULT)) {
            return null;
        }
        List<FlightDetails> flightDetailsList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_FLIGHT_DETAILS_DEFAULT);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                FlightDetails flightDetails = new FlightDetailsBuilder()
                        .withIdBuilder(Integer.parseInt(result[0]))
                        .withIdFlightBuilder(Integer.parseInt(result[1]))
                        .withDateBuilder(result[2])
                        .withStorageValumeBuilder(Long.parseLong(result[3]))
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
}
