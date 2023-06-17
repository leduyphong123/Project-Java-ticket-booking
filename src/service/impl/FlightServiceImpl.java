package service.impl;

import comparator.ComparatorIdFlight;
import constType.ConstTypeProject;
import entity.Flight;
import service.FileHandleService;
import service.FlightService;
import service.IdDefaultHandle;
import service.builder.FlightBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class FlightServiceImpl implements FlightService {

    @Override
    public boolean saveFlight(Flight flight) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_FLIGHT, true);
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
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT)) {
            return null;
        }
        List<Flight> flightList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_FLIGHT);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                Flight flight = new FlightBuilder()
                        .withId(Integer.valueOf(result[0]))
                        .withFrom_location(result[1])
                        .withTo_location(result[2])
                        .withAirline_id(Integer.valueOf(result[3]))
                        .withAirline_name(result[4])
                        .withDeparture_time(result[5])
                        .withArrival_time(result[6])
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

    @Override
    public Flight getFlightToId(int idFlight) {
        List<Flight> flightList= getAllFlight();
        for (Flight element: flightList){
            if (element.getId()==idFlight){
                return element;
            }
        }
        return null;
    }

    @Override
    public List<String> getArilineNameAll() {
        List<Flight> flightList = getAllFlight();
        Set<String> linketHashSet = new LinkedHashSet<>();
        for (Flight element : flightList){
            linketHashSet.add(element.getAirline_name());
        }
        List<String> resultList = new ArrayList<>(linketHashSet);
        return resultList;
    }

    @Override
    public String getArilineNameOne(int flightId) {
        List<Flight> flightList = getAllFlight();
        for (Flight elment :flightList){
            if (elment.getId()==flightId){
                return elment.getAirline_name();
            }
        }
        return null;
    }

    public boolean saveFlightList(List<Flight> flightList) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_FLIGHT)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_FLIGHT, false);
            bw = new BufferedWriter(fw);
            for(Flight element:flightList){
                bw.write(String.valueOf(element.getId()));
                bw.write(",");
                bw.write(element.getFrom_location());
                bw.write(",");
                bw.write(element.getTo_location());
                bw.write(",");
                bw.write(String.valueOf(element.getAirline_id()));
                bw.write(",");
                bw.write(element.getAirline_name());
                bw.write(",");
                bw.write(element.getDeparture_time());
                bw.write(",");
                bw.write(element.getArrival_time());
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

    @Override
    public boolean editFlight(Flight flightNew) {
        List<Flight> flightList = getAllFlight();
        int index=-1;
        for (int i=0;i<flightList.size();i++){
            if (flightList.get(i).getId()==flightNew.getId()){
                index = i;
                break;
            }
        }
        flightList.remove(index);
        flightList.add(flightNew);
        ComparatorIdFlight comparatorIdFlight = new ComparatorIdFlight();
        Collections.sort(flightList,comparatorIdFlight);
        return saveFlightList(flightList);
    }

    @Override
    public boolean deletFlight(int flightId) {
        List<Flight> flightList = getAllFlight();
        int index=-1;
        for (int i=0;i<flightList.size();i++){
            if (flightList.get(i).getId()==flightId){
                index = i;
                break;
            }
        }
        flightList.remove(index);
        ComparatorIdFlight comparatorIdFlight = new ComparatorIdFlight();
        Collections.sort(flightList,comparatorIdFlight);
        return saveFlightList(flightList);
    }

}
