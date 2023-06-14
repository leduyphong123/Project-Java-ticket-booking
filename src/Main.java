import constType.ConstMessenger;
import constType.ConstTypeProject;
import entity.*;
import regex.CheckDate;
import regex.CheckNumber;
import regex.CheckTimer;
import service.*;
import service.builder.*;
import service.impl.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static FlightService flightService = new FlightServiceImpl();
    public static ChairService chairService = new ChairServiceImpl();
    public static ChairDetailsService chairDetailsService = new ChairDetailsServiceImpl();
    public static StorageService storageService = new StorageServiceImpl();
    public static FlightDetailsService flightDetailsService = new FlightDetailsServiceImpl();
    public static ChairPriceService chairPriceService = new ChairPriceServiceImpl();
    public static SeatSpecsService seatSpecsService = new SeatSpecsServiceImpl();

    public static void main(String[] args) {
        int key = -1;
        do {
            System.out.println("Menu");
            System.out.println("1.NewFlight");
            System.out.println("2.ShowFLight");
            System.out.println("3.new Flight details");
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
                case 3:
                    newflightDetailsPage();
                    break;
                case 0:
                default:
                    break;
            }
        } while (key != 0);

//        newFlightPage();
//        showFlightPage();
//        newStoragePage(1);
//        newflightDetailsPage();

    }

    private static void newflightDetailsPage() {
        showMessengerEnterInformation(ConstMessenger.FLIGHT);
        showFlightPage();
        showMessengerEnterInformation(ConstMessenger.ID_FLIGHT);
        String idFlight = input.nextLine();
        String date = isCheckDateRegex(ConstMessenger.DATE_FLIGHT_DETAILS);
        long storageValume = storageService.getValumeByIdFlight(Integer.valueOf(idFlight));
        FlightDetails flightDetails = new FlightDetailsBuilder()
                .withIdBuilder(flightDetailsService.getFlightDetailsId())
                .withIdFlightBuilder(Integer.valueOf(idFlight))
                .withDateBuilder(date)
                .withStorageValumeBuilder(storageValume)
                .withUsedStorageValume(Long.valueOf("0"))
                .builder();
        boolean isResult = flightDetailsService.saveFlightDetails(flightDetails);
        if (isResult) {
            int idFlightDetails = flightDetailsService.getFlightDetailsId();
            newChairPricePage(
                    ConstTypeProject.LIST_CHAIR_PRICE_ID_DEFAULT[0],
                    ConstTypeProject.TYPE_SKY_BOSS,
                    idFlightDetails);
            newChairPricePage(
                    ConstTypeProject.LIST_CHAIR_PRICE_ID_DEFAULT[1],
                    ConstTypeProject.TYPE_DELUXE,
                    idFlightDetails);
            newChairPricePage(
                    ConstTypeProject.LIST_CHAIR_PRICE_ID_DEFAULT[2],
                    ConstTypeProject.TYPE_ORIGINAL,
                    idFlightDetails);
            IdDefaultHandle.writeIdDefault(idFlightDetails + 1, ConstTypeProject.PATH_FLIGHT_DETAILS_ID);
            renderSeatSpecs(Integer.valueOf(idFlight),idFlightDetails);
        }
    }

    private static void newChairPricePage(int chairPriceId, String type, int idFlightDetail) {
        String price = isCheckNumberRegex(ConstTypeProject.PRICE + type);
        ChairPrice chairPrice = new ChairPriceBuilder()
                .withIdBuilder(chairPriceId)
                .withTypeBuilder(type)
                .withPriceBuilder(Long.valueOf(price))
                .builder();
        chairPriceService.saveChairPrice(chairPrice, idFlightDetail);
    }

    private static void newStoragePage(int flightId) {
        showMessengerEnterInformation("storage");
        String valume = isCheckNumberRegex(ConstMessenger.STORAGE_VALUME);
        Storage storage = new StorageBuilder()
                .withIdBuilder(Integer.valueOf(storageService.getStorageId()))
                .withIdFlightBuilder(Integer.valueOf(flightId))
                .withValumeBuilder(Long.valueOf(valume))
                .builder();
        boolean isResult = storageService.saveStorage(storage);
        if (isResult) {
            IdDefaultHandle.writeIdDefault(storageService.getStorageId() + 1, ConstTypeProject.PATH_STORAGE_ID_DEFAULT);
        }
    }

    private static void newChairPage(int idFlight) {
        String lineQuantity = isCheckNumberRegex(ConstMessenger.LINE_QUANTITY);

        Chair chair = new ChairBuilder()
                .withIdBuilder(chairService.getChairId())
                .withIdFlightBuilder(idFlight)
                .withLineQuantityBuilder(Integer.valueOf(lineQuantity))
                .builder();
        boolean isResult = chairService.saveChair(chair);
        if (isResult) {
            Chair chairResult = chairService.getChair();
            IdDefaultHandle.writeIdDefault(chairResult.getId() + 1, ConstTypeProject.PATH_CHAIR_ID_DEFAULT);
            for (int i = 0; i < chairResult.getLineQuantity(); i++) {
                showMessengerEnterInformation("for the sequence " + ConstTypeProject.LIST_LINE_DEFAULT[i]);
                addListChairDetailPage(chairResult, i, ConstTypeProject.TYPE_SKY_BOSS);
                addListChairDetailPage(chairResult, i, ConstTypeProject.TYPE_DELUXE);
                addListChairDetailPage(chairResult, i, ConstTypeProject.TYPE_ORIGINAL);
                chairDetailsService.setIndexDefault();
            }
        }
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
            newStoragePage(flightService.getFlightId());
            newChairPage(flightService.getFlightId());
            IdDefaultHandle.writeIdDefault(flightService.getFlightId() + 1, ConstTypeProject.PATH_FLIGHT_ID);
            System.out.println("new succes");
        }
    }
    private static void renderSeatSpecs(int idFlight,int idFlightDetail){
        List<ChairDetails> chairDetailsList = chairDetailsService.getAllByFlightId(idFlight);
        List<ChairPrice> chairPriceList = chairPriceService.getAllByFlightId(idFlightDetail);
        seatSpecsService.saveSeatSpecsList(chairDetailsList,chairPriceList,idFlightDetail);
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
        return CheckTimer.formatTime(time);
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

    private static String isCheckDateRegex(String type) {
        int key = -1;
        String date;
        do {
            showMessengerEnterInformation(type);
            date = input.nextLine();
            if (CheckDate.isDate(date)) {
                key = 0;
            } else {
                showMessengerEnterInformation(" format");
            }
        } while (key != 0);
        return CheckDate.formatDate(date);
    }

    private static void showFlightPage() {
        System.out.println("list Flight Page");
        List<Flight> list = flightService.getAllFlight();
        for (Flight elment : list) {
            System.out.println(elment);
        }
    }
}