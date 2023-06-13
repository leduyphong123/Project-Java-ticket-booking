package service.impl;

import constType.ConstTypeProject;
import entity.Flight;
import service.FileHandleService;
import service.builder.FlightBuilder;
import service.FlightService;
import service.IdDefaultHandle;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {

    @Override
    public boolean saveFlight(Flight flight) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DEFAULT)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_FLIGHT_DEFAULT, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(flight.getId()));
            bw.write(",");
            bw.write(flight.getFrom_location());
            bw.write(",");
            bw.write(flight.getTo_location());
            bw.write(",");
            bw.write(String.valueOf(flight.getAirline_id()));
            bw.write(",");
            bw.write(flight.getAirline_name());
            bw.write(",");
            bw.write(flight.getDeparture_time());
            bw.write(",");
            bw.write(flight.getArrival_time());
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
    public List<Flight> getAllFlight() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_DEFAULT)) {
            return null;
        }
        List<Flight> flightList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_FLIGHT_DEFAULT);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                Flight flight = new FlightBuilder()
                        .withIdBuilder(Integer.valueOf(result[0]))
                        .withFrom_locationBuilder(result[1])
                        .withTo_locationBuilder(result[2])
                        .withAirline_idBuilder(Integer.valueOf(result[3]))
                        .withAirline_nameBuilder(result[4])
                        .withDeparture_timeBuilder(result[5])
                        .withArrival_timeBuilder(result[6])
                        .builder();
                flightList.add(flight);
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
        return flightList;
    }

    @Override
    public int getFlightId() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT_ID)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_FLIGHT_ID).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_FLIGHT_ID);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_FLIGHT_ID);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

}
