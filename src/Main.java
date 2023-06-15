import constType.ConstMessenger;
import constType.ConstRegex;
import constType.ConstTypeProject;
import entity.*;
import regex.*;
import service.*;
import service.builder.*;
import service.factory.SearchFactory;
import service.impl.*;

import java.util.*;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static FlightService flightService = new FlightServiceImpl();
    public static ChairService chairService = new ChairServiceImpl();
    public static ChairDetailsService chairDetailsService = new ChairDetailsServiceImpl();
    public static StorageService storageService = new StorageServiceImpl();
    public static FlightDetailsService flightDetailsService = new FlightDetailsServiceImpl();
    public static ChairPriceService chairPriceService = new ChairPriceServiceImpl();
    public static SeatSpecsService seatSpecsService = new SeatSpecsServiceImpl();
    public static LuggagePriceService luggagePService = new LuggagePriceServiceImpl();
    public static TicketService ticketService = new TicketServiceImpl();
    public static TicketDetailService ticketDetailService = new TicketDetailsServiceImpl();
    public static AcountService acountService = new AcountServiceImpl();
    public static void main(String[] args) {
//        int key;
//        do {
//            System.out.println("Menu");
//            System.out.println("1.NewFlight");
//            System.out.println("2.ShowFLight");
//            System.out.println("3.New Luggage Price airline");
//            System.out.println("4.new Flight details");
//            System.out.println("5.Book ticket");
//            System.out.println("6.Register");
//            System.out.println("7.Login");
//            System.out.println("0.Exit");
//            key = input.nextInt();
//            input.nextLine();
//            switch (key) {
//                case 1:
//                    newFlightPage();
//                    break;
//                case 2:
//                    showFlightPage();
//                    break;
//                case 3:
//                    newLuggagePricePage();
//                    break;
//                case 4:
//                    newflightDetailsPage();
//                    break;
//                case 5:
//                      viewBookTicket();
//                    break;
//                case 6:
//                      newRegisterPage("user");
//                    break;
//                case 7:
//                      newLoginPage();
//                    break;
//                case 0:
//                default:
//                    break;
//            }
//        } while (key != 0);

//        newFlightPage();
//        showFlightPage();
//        newStoragePage(1);
//        newflightDetailsPage();
//        newflightDetailsPage();

//        newRegisterPage("user");
//        newLoginPage();

    }

    private static void newLoginPage() {
        int key=-1;
        do {
            System.out.println("Login");
            String email = isCheckEmailRegex("email");
            showMessengerEnterInformation("password");
            String passworld = input.nextLine();
            boolean isResult = acountService.checkAcount(email,passworld);
            if (isResult){
                key = 0;
                System.out.println("login sussec");
            }else {
                showMessengerError("wrong email password");
            }
        }while (key!=0);
    }

    private static void newRegisterPage(String type) {
        System.out.println("Register");
        String email = getEmail();
        String name = isCheckNameRegex("full name", ConstRegex.FULL_NAME_REGEX);
        String password = getPassword();
        Acount acount = new AcountBuilder()
                .withIdBuilder(1)
                .withEmailBuilder(email)
                .withPasswordBuilder(password)
                .withTypeBuilder(type)
                .builder();
        boolean isResult= acountService.saveAcount(acount);
        if (isResult){
            IdDefaultHandle.writeIdDefault(acountService.getAcountId()+1,ConstTypeProject.PATH_ACOUNT_ID);
            System.out.println("succes");
        }
    }

    private static String getPassword() {
        String password=null;
        int key = -1;
        do {
            showMessengerEnterInformation("password");
            String passwordOne = input.nextLine();
            showMessengerEnterInformation("the password");
            String passwordTwo = input.nextLine();
            if (passwordOne.equals(passwordTwo)) {
                password = passwordOne;
                key = 0;
            } else {
                System.err.println("Incorrect password");
            }
        } while (key != 0);
        return password;
    }

    private static String getEmail() {
        int key=-1;
        String email;
        do {
            email = isCheckEmailRegex("email");
            boolean isEmailExist = acountService.checkEmail(email);
            if (isEmailExist==false){
                key=0;
            }else {
                showMessengerError("Already exist");
            }
        }while (key!=0);
        return email;
    }

    private static void viewBookTicket() {
        Map<Long, FlightDetails> map = getLongFlightDetailsMap();
        showFlightAll(map);
        showMessengerEnterInformation("code");
        String flightDetailsId = input.nextLine().trim();
        List<ChairPrice> tempChairPriceList = chairPriceService.getAllByFlightDetailId(Integer.parseInt(flightDetailsId));
        showTempChairPriceList(tempChairPriceList);
        showMessengerEnterInformation("code chair");
        String chairPriceId = input.nextLine().trim();
        ChairPrice chairPrice = tempChairPriceList.get(Integer.parseInt(chairPriceId) - 1);

        String email = isCheckEmailRegex("email");

        String title = getTitle("title", ConstTypeProject.LIST_TITLE);
        String lastName = isCheckNameRegex("flast name", ConstRegex.LAST_NAME_REGEX);
        String firtName = isCheckNameRegex("firt name", ConstRegex.FIRT_NAME_REGEX);
        String dateOfBirth = isCheckBirthRegex("date of birth");

        String nationality = getNationality();

        String luggagePriceId = getLuggaePriceId(flightDetailsId);
        String payment = getPayment();

        List<SeatSpecs> seatSpecsList = seatSpecsService.getTypeAndId(Integer.parseInt(flightDetailsId), chairPrice);
        showListChair(seatSpecsList);
        SeatSpecs seatSpecs = getSeatSpecs(seatSpecsList);

        boolean isResult = seatSpecsService.editFile(seatSpecs, flightDetailsId);
        int flightId = flightDetailsService.getFlightId(flightDetailsId);
        if (isResult) {
            long valume = getValume(luggagePriceId, flightId);
            boolean isMessenger = newTicketPage(title, lastName, firtName, flightId, valume);
            if (isMessenger) {
                boolean isSucces = newTicketDetailsPage(email, title, lastName, firtName, dateOfBirth, nationality, payment);
                if (isSucces) {
                    System.out.println("succes");
                    IdDefaultHandle.writeIdDefault(ticketService.getTicketId() + 1, ConstTypeProject.PATH_TICKET_ID);
                }
            }
        }
    }

    private static String getTitle(String messenger, String[] listTitle) {
        String title = null;
        int key = -1;
        do {
            showMessengerEnterInformation(messenger);
            for (int i = 0; i < listTitle.length; i++) {
                System.out.println(i + 1 + ". " + listTitle[i]);
            }
            title = isCheckNumberRegex(title);
            title = listTitle[Integer.parseInt(title) - 1];
            if (title != null) {
                key = 0;
            }
        } while (key != 0);
        return title;
    }

    private static long getValume(String luggagePriceId, int flightId) {
        long valume = 7;
        LuggagePrice luggagePrice = null;
        if (luggagePriceId != null) {
            String airlineName = flightService.getArilineNameOne(flightId);
            luggagePrice = luggagePService.getLPFromLPId(airlineName, luggagePriceId);
            valume += luggagePrice.getValume();
        }
        return valume;
    }

    private static boolean newTicketDetailsPage(String email, String title, String lastName, String firtName, String dateOfBirth, String nationality, String payment) {
        TiketDetails tiketDetails = new TiketDetailsBuilder()
                .withTicketIdBuilder(ticketService.getTicketId())
                .withEmailBuilder(email)
                .withTitleBuilder(title)
                .withLastName(lastName)
                .withFirtName(firtName)
                .withDateOfBirthBuilder(dateOfBirth)
                .withNationalityBuilder(nationality)
                .withPayment(payment)
                .builder();
        boolean isSucces = ticketDetailService.saveTicketDetail(tiketDetails);
        return isSucces;
    }

    private static boolean newTicketPage(String title, String lastName, String firtName, int flightId, long valume) {
        Flight flight = flightService.getFlightToId(flightId);
        Ticket ticket = new TicketBuilder()
                .withIdBuilder(ticketService.getTicketId())
                .withUserIdBuilder(1)
                .withFullName(title + ": " + firtName + lastName)
                .withValumeBuilder(valume)
                .withAirlineCode(flight.getAirline_name() + "-" + flight.getAirline_id())
                .withDepartureTimeBuilder(flight.getDeparture_time())
                .withAirlineTimeBuilder(flight.getArrival_time())
                .withStatusBuilder(Boolean.parseBoolean("true"))
                .builder();
        boolean isMessenger = ticketService.saveTicket(ticket);
        return isMessenger;
    }

    private static String getNationality() {
        String nationality = getTitle("notionality", ConstTypeProject.LIST_NOTIONNALITY);
        return nationality;
    }

    private static SeatSpecs getSeatSpecs(List<SeatSpecs> seatSpecsList) {
        SeatSpecs seatSpecs = null;
        int key = -1;
        do {
            showMessengerEnterInformation("chairName");
            String chairName = input.nextLine().trim();
            for (SeatSpecs elment : seatSpecsList) {
                if (elment.getChairName().equals(chairName) && elment.isStatus() == true) {
                    seatSpecs = elment;
                    key = 0;
                }
            }
            if (key != 0) {
                showMessengerError("chair has been booked");
            }
        } while (key != 0);
        return seatSpecs;
    }

    private static String getPayment() {
        String payment = null;
        int key;
        do {
            System.out.println("Payment ?");
            System.out.println("1.Yes");
            System.out.println("2.No");
            key = Integer.parseInt(isCheckNumberRegex("key"));
            switch (key) {
                case 1:
                    payment = isCheckNumberRegex("code");
                    if (payment != null) {
                        key = 2;
                    }
                    break;
                case 2:
                    key = 2;
                    break;
            }
        } while (key != 2);
        return payment;
    }

    private static String getLuggaePriceId(String flightDetailsId) {
        String luggagePriceId = null;
        int key;
        do {
            System.out.println("Do you want to buy more luggage?");
            System.out.println("1.Yes");
            System.out.println("2.No");
            key = Integer.parseInt(isCheckNumberRegex("key"));
            switch (key) {
                case 1:
                    int flightId = flightDetailsService.getFlightId(flightDetailsId);
                    String airlineName = flightService.getArilineNameOne(flightId);
                    getShowLuggagePriceList(String.valueOf(airlineName));
                    luggagePriceId = isCheckNumberRegex("code");
                    if (luggagePriceId != null) {
                        key = 2;
                    }
                case 2:
                    key = 2;
                    break;
            }
        } while (key != 2);
        return luggagePriceId;
    }

    private static void showListChair(List<SeatSpecs> seatSpecsList) {
        System.out.println("List chair");
        int count = 0;
        for (SeatSpecs element : seatSpecsList) {
            System.out.print("Chair{chairName='" + element.getChairName() + "' status=" + element.isStatus() + "}");
            if (count == 4) {
                System.out.println();
                count = 0;
            } else {
                System.out.print("\t");
                count++;
            }
        }
    }

    private static void showTempChairPriceList(List<ChairPrice> tempChairPriceList) {
        for (int i = tempChairPriceList.size() - 1; i >= 0; i--) {
            System.out.println(tempChairPriceList.get(i));
        }
    }

    private static void showFlightAll(Map<Long, FlightDetails> map) {
        System.out.println("Flight all");
        for (Map.Entry<Long, FlightDetails> entry : map.entrySet()) {
            Flight flight = flightService.getFlightToId(entry.getValue().getIdFlight());
            System.out.println("Flight{" +
                    "code='" + entry.getValue().getId() + '\'' +
                    ", airline_name='" + flight.getAirline_name() + '\'' +
                    ", from_location='" + flight.getFrom_location() + '\'' +
                    ", to_location='" + flight.getTo_location() + '\'' +
                    ", departure_time='" + flight.getDeparture_time() + '\'' +
                    ", arrival_time='" + flight.getArrival_time() + '\'' +
                    ", price ='" + entry.getKey() + '\'' +
                    ", date ='" + entry.getValue().getDate() + '\'' +
                    '}');
        }
    }

    private static Map<Long, FlightDetails> getLongFlightDetailsMap() {
        Map<Long, FlightDetails> map = new TreeMap<>();
        List<FlightDetails> dateList;
        int key = -1;
        do {
            showMessengerEnterInformation(ConstMessenger.FROM_LOCATION);
            String from = input.nextLine().trim();
            showMessengerEnterInformation(ConstMessenger.TO_LOCATION);
            String to = input.nextLine().trim();
            showMessengerEnterInformation(ConstMessenger.DATE_FLIGHT_DETAILS);
            String date = input.nextLine().trim();
            Search searchFrom = SearchFactory.getSearchFatory(ConstTypeProject.TYPE_FORM);
            Search searchTo = SearchFactory.getSearchFatory(ConstTypeProject.TYPE_TO);
            Search searchDate = SearchFactory.getSearchFatory(ConstTypeProject.TYPE_DATE);
            List<FlightDetails> flightDetailsList = flightDetailsService.getAllList();
            List<FlightDetails> fromList = searchFrom.searchFlightDetailsList(flightDetailsList, from);
            List<FlightDetails> toList = searchTo.searchFlightDetailsList(fromList, to);
            dateList = searchDate.searchFlightDetailsList(toList, date);
            if (dateList.size() != 0) {
                key = 0;
            } else {
                System.out.println("Flight not date");
            }
        } while (key != 0);

        for (int i = 0; i < dateList.size(); i++) {
            FlightDetails tempFlightDetails = dateList.get(i);
            List<ChairPrice> tempChairPriceList = chairPriceService.getAllByFlightDetailId(tempFlightDetails.getIdFlight());
            map.put(tempChairPriceList.get(2).getPrice(), tempFlightDetails);
        }
        return map;
    }

    private static void newLuggagePricePage() {
        System.out.println("New Luggage Price Page");
        getShowArilineNameList();
        showMessengerEnterInformation("airline name");
        String airlineName = input.nextLine().trim();
        getShowLuggagePriceList(airlineName);
        showMessengerEnterInformation("name");
        String name = input.nextLine().trim();
        String valume = isCheckNumberRegex("valume");
        String price = isCheckNumberRegex("price");
        LuggagePrice luggagePrice = new LuggagePriceBuilder()
                .withIdBuilder(luggagePService.getLuggagePriceId(airlineName))
                .withNameBuilder(name)
                .withValumeBuilder(Long.parseLong(valume))
                .withPriceBuilder(Long.parseLong(price))
                .builder();
        boolean isResult = luggagePService.saveLuggagePrice(luggagePrice, airlineName);
        if (isResult) {
            IdDefaultHandle.writeIdDefault(
                    luggagePService.getLuggagePriceId(airlineName) + 1,
                    ConstTypeProject.PATH_LUGGAGE_ID
                            + airlineName
                            + ConstTypeProject.CSV);
            System.out.println("success");
        }
    }

    private static void getShowLuggagePriceList(String airlineName) {
        List<LuggagePrice> luggagePriceList = luggagePService.getAirlineNameList(airlineName);
        if (luggagePriceList.size() != 0) {
            System.out.println("Luggage Price List");
            for (LuggagePrice element : luggagePriceList) {
                System.out.println(element);
            }
        }
    }

    private static void getShowArilineNameList() {
        List<String> ariLineNameList = flightService.getArilineNameAll();
        System.out.println("List airline name");
        for (String elment : ariLineNameList) {
            System.out.println(elment);
        }
    }

    private static void newflightDetailsPage() {
        showMessengerEnterInformation(ConstMessenger.FLIGHT);
        showFlightPage();
        showMessengerEnterInformation(ConstMessenger.ID_FLIGHT);
        String idFlight = input.nextLine().trim();
        String date = isCheckDateRegex(ConstMessenger.DATE_FLIGHT_DETAILS);
        long storageValume = storageService.getValumeByIdFlight(Integer.parseInt(idFlight));
        FlightDetails flightDetails = new FlightDetailsBuilder()
                .withIdBuilder(flightDetailsService.getFlightDetailsId())
                .withIdFlightBuilder(Integer.parseInt(idFlight))
                .withDateBuilder(date)
                .withStorageValumeBuilder(storageValume)
                .withUsedStorageValume(Long.parseLong("0"))
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
            renderSeatSpecs(Integer.parseInt(idFlight), idFlightDetails);
        }
    }

    private static void newChairPricePage(int chairPriceId, String type, int idFlightDetail) {
        String price = isCheckNumberRegex(ConstTypeProject.PRICE + type);
        ChairPrice chairPrice = new ChairPriceBuilder()
                .withIdBuilder(chairPriceId)
                .withTypeBuilder(type)
                .withPriceBuilder(Long.parseLong(price))
                .builder();
        chairPriceService.saveChairPrice(chairPrice, idFlightDetail);
    }

    private static void newStoragePage(int flightId) {
        showMessengerEnterInformation("storage");
        String valume = isCheckNumberRegex(ConstMessenger.STORAGE_VALUME);
        Storage storage = new StorageBuilder()
                .withIdBuilder(storageService.getStorageId())
                .withIdFlightBuilder(flightId)
                .withValumeBuilder(Long.parseLong(valume))
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
                .withLineQuantityBuilder(Integer.parseInt(lineQuantity))
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

    private static void showMessengerError(String mesenger) {
        System.err.println( mesenger);
    }

    private static void newFlightPage() {
        showMessengerEnterInformation("for the sequence new Flight");
        showMessengerEnterInformation(ConstMessenger.FROM_LOCATION);
        String fromLocation = input.nextLine().trim();
        showMessengerEnterInformation(ConstMessenger.TO_LOCATION);
        String toLocation = input.nextLine().trim();
        showMessengerEnterInformation(ConstMessenger.AIRLINE_ID);
        String airlineId = input.nextLine().trim();
        showMessengerEnterInformation(ConstMessenger.AIRLINE_NAME);
        String airlineName = input.nextLine().trim();
        String departureTime = isCheckTimerRegex(ConstMessenger.DEPARTURE_TIME);
        String arrivalTime = isCheckTimerRegex(ConstMessenger.ARRIVAL_TIME);
        Flight flight = new FlightBuilder()
                .withIdBuilder(flightService.getFlightId())
                .withFrom_locationBuilder(fromLocation)
                .withTo_locationBuilder(toLocation)
                .withAirline_idBuilder(Integer.parseInt(airlineId))
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

    private static void renderSeatSpecs(int idFlight, int idFlightDetail) {
        List<ChairDetails> chairDetailsList = chairDetailsService.getAllByFlightId(idFlight);
        List<ChairPrice> chairPriceList = chairPriceService.getAllByFlightDetailId(idFlightDetail);
        seatSpecsService.saveSeatSpecsList(chairDetailsList, chairPriceList, idFlightDetail);
    }

    private static String isCheckTimerRegex(String messenger) {
        int key = -1;
        String time;
        do {
            showMessengerEnterInformation(messenger);
            time = input.nextLine().trim();
            if (CheckTimer.isTime(time)) {
                key = 0;
            } else {
                showMessengerError(" format");
            }
        } while (key != 0);
        return CheckTimer.formatTime(time);
    }

    private static String isCheckNumberRegex(String messenger) {
        int key = -1;
        String number;
        do {
            showMessengerEnterInformation(messenger);
            number = input.nextLine().trim();
            if (CheckNumber.isNumber(number)) {
                key = 0;
            } else {
                showMessengerError(" format");
            }
        } while (key != 0);
        return number;
    }

    private static String isCheckEmailRegex(String messenger) {
        int key = -1;
        String number;
        do {
            showMessengerEnterInformation(messenger);
            number = input.nextLine().trim();
            if (CheckEmail.isEmail(number)) {
                key = 0;
            } else {
                showMessengerError(" format");
            }
        } while (key != 0);
        return number;
    }

    private static String isCheckNameRegex(String messenger, String constType) {
        int key = -1;
        String number;
        do {
            showMessengerEnterInformation(messenger);
            number = input.nextLine().trim();
            if (CheckName.isName(number, constType)) {
                key = 0;
            } else {
                showMessengerError(" format");
            }
        } while (key != 0);
        return number;
    }

    private static String isCheckDateRegex(String messenger) {
        int key = -1;
        String date;
        do {
            showMessengerEnterInformation(messenger);
            date = input.nextLine().trim();
            if (CheckDate.isDate(date)) {
                key = 0;
            } else {
                showMessengerError("format");
            }
        } while (key != 0);
        return CheckDate.formatDate(date);
    }

    private static String isCheckBirthRegex(String messenger) {
        int key = -1;
        String date;
        do {
            showMessengerEnterInformation(messenger);
            date = input.nextLine().trim();
            if (CheckBirth.isDate(date)) {
                key = 0;
            } else {
                showMessengerError("format");
            }
        } while (key != 0);
        return CheckBirth.formatDate(date);
    }

    private static void showFlightPage() {
        System.out.println("list Flight Page");
        List<Flight> list = flightService.getAllFlight();
        for (Flight elment : list) {
            System.out.println(elment);
        }
    }
}