package service.impl;

import constType.ConstTypeProject;
import entity.FlightDetails;
import service.FileHandleService;
import service.FlightDetailsService;
import service.IdDefaultHandle;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
}
