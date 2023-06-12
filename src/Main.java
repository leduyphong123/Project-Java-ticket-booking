import constType.ConstMessenger;
import constType.ConstTypeProject;
import entity.Chair;
import entity.ChairDetails;
import entity.Flight;
import regex.CheckNumber;
import regex.CheckTimer;
import service.*;
import service.impl.ChairDetailsServiceImpl;
import service.impl.ChairServiceImpl;
import service.impl.FlightServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static FlightService flightService = new FlightServiceImpl();
    public static ChairService chairService = new ChairServiceImpl();
    public static ChairDetailsService chairDetailsService = new ChairDetailsServiceImpl();

    public static void main(String[] args) {
        int key = -1;
        do {
            System.out.println("Menu");
            System.out.println("1.NewFlight");
            System.out.println("2.ShowFLight");
            System.out.println("0.Exit");
            key = input.nextInt();
            input.nextLine();
            switch (key) {
                case 1:
                    newFlightPage();
                    break;
                case 2:
                    showFlightPage();
                    break;
                case 0:
                default:
                    break;
            }
        } while (key != 0);
//        newFlightPage();
//        showFlightPage();
    }

    private static void newChairPage(int idFlight) {
        String lineQuantity = isCheckNumberRegex(ConstMessenger.LINE_QUANTITY);

        Chair chair = new ChairBuilder()
                .withIdBuilder(chairService.getChairId())
                .withIdFlightBuilder(idFlight)
                .withLineQuantityBuilder(Integer.valueOf(lineQuantity))
                .builder();
        boolean isResult = chairService.saveChair(chair);
        if (isResult){
            Chair chairResult = chairService.getChair();
            IdDefaultHandle.writeIdDefault(chairResult.getId()+1,ConstTypeProject.PATH_CHAIR_ID_DEFAULT);
            for (int i = 0; i < chairResult.getLineQuantity(); i++) {
                showMessengerEnterInformation("for the sequence " + ConstTypeProject.LIST_LINE_DEFAULT[i]);
                addListChairDetailPage(chairResult, i, ConstTypeProject.TYPE_SKY_BOSS);
                addListChairDetailPage(chairResult, i, ConstTypeProject.TYPE_DELUXE);
                addListChairDetailPage(chairResult, i, ConstTypeProject.TYPE_ORIGINAL);
                chairDetailsService.setIndexDefault();
            }
        }
    }

    private static String isCheckNumberRegex(String type) {
        int key = -1;
        String number;
        do {
            showMessengerEnterInformation(type);
            number = input.nextLine();
            if (CheckNumber.isNumber(number)) {
                key = 0;
            } else {
                showMessengerEnterInformation(" format");
            }
        } while (key != 0);
        return number;
    }

    private static void addListChairDetailPage(Chair chairResult, int i, String type) {
        showMessengerEnterInformation(type);
        int quantityChairSkyBoss = input.nextInt();
        input.nextLine();
        chairDetailsService.saveListChairDetails(
                chairResult,
                ConstTypeProject.LIST_LINE_DEFAULT[i],
                quantityChairSkyBoss,
                type);
    }

    private static void showMessengerEnterInformation(String mesenger) {
        System.out.println("Enter information " + mesenger);
    }

    private static void newFlightPage() {
        showMessengerEnterInformation("for the sequence new Flight");
        showMessengerEnterInformation(ConstMessenger.FROM_LOCATION);
        String fromLocation = input.nextLine();
        showMessengerEnterInformation(ConstMessenger.TO_LOCATION);
        String toLocation = input.nextLine();
        showMessengerEnterInformation(ConstMessenger.AIRLINE_ID);
        String airlineId = input.nextLine();
        showMessengerEnterInformation(ConstMessenger.AIRLINE_NAME);
        String airlineName = input.nextLine();
        String departureTime = isCheckTimerRegex(ConstMessenger.DEPARTURE_TIME);
        String arrivalTime = isCheckTimerRegex(ConstMessenger.ARRIVAL_TIME);
        showMessengerEnterInformation(ConstMessenger.ARRIVAL_TIME);
        Flight flight = new FlightBuilder()
                .withIdBuilder(flightService.getFlightId())
                .withFrom_locationBuilder(fromLocation)
                .withTo_locationBuilder(toLocation)
                .withAirline_idBuilder(Integer.valueOf(airlineId))
                .withAirline_nameBuilder(airlineName)
                .withDeparture_timeBuilder(departureTime)
                .withArrival_timeBuilder(arrivalTime)
                .builder();
        boolean result = flightService.saveFlight(flight);
        if (result) {
            newChairPage(flightService.getFlightId());
            IdDefaultHandle.writeIdDefault(flightService.getFlightId() + 1, ConstTypeProject.PATH_FLIGHT_ID);
            System.out.println("new succes");
        }
    }

    private static String isCheckTimerRegex(String type) {
        int key = -1;
        String time;
        do {
            showMessengerEnterInformation(type);
            time = input.nextLine();
            if (CheckTimer.isTime(time)) {
                key = 0;
            } else {
                showMessengerEnterInformation(" format");
            }
        } while (key != 0);
        return time;
    }

    private static void showFlightPage() {
        System.out.println("list Flight Page");
        List<Flight> list = flightService.getAllFlight();
        for (Flight elment : list) {
            System.out.println(elment);
        }
    }
}