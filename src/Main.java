import static constType.ConstMessenger.*;
import constType.ConstRegex;
import entity.*;
import regex.*;
import service.*;
import service.builder.*;
import service.factory.SearchFactory;
import service.factory.SearchTicketFactory;
import service.impl.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static constType.ConstTypeProject.*;

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
    public static Acount acountSession = null;

    public static void main(String[] args) {
//        newRegisterPage(TYPE_ADMIN);

        int keys;
        do {
            if (acountSession != null) {
                if (acountSession.getType().equals(TYPE_USER)) {
                    viewMenuUser();
                } else if (acountSession.getType().equals(TYPE_ADMIN)) {
                    viewMenuAdmin();
                } else if (acountSession.getType().equals(TYPE_STAFF)) {
                    viewMenuStaff();
                }
            }
            System.out.println("Menu");
            System.out.println("1.Register");
            System.out.println("2.Login");
            System.out.println("3.Book ticket");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    newRegisterPage(TYPE_USER);
                    break;
                case 2:
                    newLoginPage();
                    break;
                case 3:
                    viewBookTicket();
                    break;
                case 0:
                default:
                    break;
            }
        } while (keys != 0);

    }

    private static void viewMenuUser() {
        int key;
        do {
            System.out.println("Menu");
            System.out.println("1.Book ticket");
            System.out.println("2.History ticket");
            System.out.println("3.Profile");
            System.out.println("4.New PassWord");
            System.out.println("5.Logout");
            key = input.nextInt();
            input.nextLine();
            switch (key) {
                case 1:
                    viewBookTicket();
                    break;
                case 2:
                    showHistoryTicketUser();
                    break;
                case 3:
                    System.out.println(acountSession);
                    break;
                case 4:
                    int keys=-1;
                    while (keys!=0){
                        showMessengerEnterInformation("old password");
                        String passworlOld= input.nextLine();
                        if (acountSession.getPassword().equals(passworlOld)){
                            keys=0;
                        }else {
                            showMessengerError("old passworld false");
                        }
                    }
                    String password = getPassword();
                    acountSession.setPassword(password);
                    boolean isResult= acountService.newPassworld(acountSession);
                    if (isResult){
                        System.out.println("New passwold succes");
                    }else {
                        System.out.println("error");
                    }

                    break;
                case 5:
                    logout();
                    key = 0;
                    break;
            }
        } while (key != 0);
    }
    private static void viewMenuAdmin() {
        int key;
        do {
            System.out.println("Menu");
            System.out.println("1.Flight");
            System.out.println("2.Storage");
            System.out.println("3.Chair");
            System.out.println("4.Account");
            System.out.println("5.Logout");
            key = input.nextInt();
            input.nextLine();
            switch (key) {
                case 1:
                    flightPageAdmin();
                    break;
                case 2:
                    storagePageAdmin();
                    break;
                case 3:
                    chairPageAdmin();
                    break;
                case 4:
                    acountPageAdmin();
                    break;
                case 5:
                    logout();
                    key = 0;
                    break;
            }
        } while (key != 0);
    }
    private static void viewMenuStaff() {
        int key;
        do {
            System.out.println("Menu");
            System.out.println("1.FLight Details");
            System.out.println("2.Luggage ");
            System.out.println("3.Ticket ");
            System.out.println("4.Logout ");
            key = input.nextInt();
            input.nextLine();
            switch (key) {
                case 1:
                    flightDetailsPageStaff();
                    break;
                case 2:
                    luggagePricePageStaff();
                    break;
                case 3:
                    ticketPageStaff();
                    break;
                case 4:
                    logout();
                    break;
            }
        } while (key != 0);
    }
    private static void showMenuFlightDetails() {
        int keys;
        do {
            System.out.println("Menu show flight details");
            System.out.println("1. show get date");
            System.out.println("2. show get all");
            System.out.println("0. exit");
            keys = Integer.parseInt(isCheckNumberRegex("key"));
            List<FlightDetails> flightDetailsList = flightDetailsService.getAllList();
            switch (keys) {
                case 1:
                    String date = isCheckDateInputRegex(TYPE_DATE);
                    Search searchDate = SearchFactory.getSearchFatory(TYPE_DATE);
                    List<FlightDetails> detailsListDate = searchDate.searchList(flightDetailsList, date);
                    if (detailsListDate.size()==0){
                        System.out.println("No flight details list");
                        break;
                    }

                    showFlightDetails(detailsListDate);
                    break;
                case 2:
                    showFlightDetails(flightDetailsList);
                    break;
                default:
                    keys=0;
                    break;
            }
        }while (keys!=0);
    }

    private static void showFlightDetails(List<FlightDetails> detailsListDate) {
        for (FlightDetails element : detailsListDate) {
            String chair= seatSpecsService.getChairBlank(element.getId());
            System.out.println(element+chair+"{seat status= "+chair+"}");
        }
    }

    private static void ticketPageStaff() {
        int keys;
        do {
            System.out.println("Menu Ticket");
            System.out.println("1.Show Ticket All");
            System.out.println("2.Search Ticket");
            System.out.println("3.Check in");
//            System.out.println("9.Edit Ticket");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    List<Ticket> ticketList = ticketService.getAll();
                    showTicket(ticketList);
                    break;
                case 2:
                    searchTicket();
                    break;
                case 3:
                    String ticketId = isCheckNumberRegex("ticket id");
                    boolean isResult = ticketService.checkIn(Integer.parseInt(ticketId));
                    if (isResult) {
                        System.out.println("Check in succes");
                    } else {
                        showMessengerError("Check in error");
                    }
                    break;
//                case 9:
//                    int ticketId = Integer.parseInt(isCheckNumberRegex("ticket id"));
//                    boolean isExit = ticketService.isExit(ticketId);
//                    if (!isExit) {
//                        showMessengerError("Not ticket id");
//                        break;
//                    }
//
//                    break;
                case 0:
                    keys = 0;
                    break;
                default:
                    break;
            }
        } while (keys != 0);
    }

    private static void searchTicket() {
        String fullName = isCheckNameRegex("full name", ConstRegex.FULL_NAME_REGEX);
        showMessengerEnterInformation("airline code");
        String airlineCode = input.nextLine().toUpperCase();
        SearchTicket searchTicketFull = SearchTicketFactory.getSearchFatory("fullName");
        SearchTicket searchTicketAri = SearchTicketFactory.getSearchFatory("arialineCode");
        List<Ticket> tickets = ticketService.getAll();
        List<Ticket> ticketFullName = searchTicketFull.searchList(tickets, fullName);
        if (ticketFullName == null) {
            showMessengerError("Not name");
            return;
        }
        List<Ticket> ticketAri = searchTicketAri.searchList(ticketFullName, airlineCode);
        if (ticketAri == null) {
            showMessengerError("Not arialine");
            return;
        }
        showTicket(ticketAri);
    }

    private static void showTicket(List<Ticket> ticketList) {
        if (ticketList.size()==0){
            System.out.println("No ticket list");
        }
        for (Ticket element : ticketList) {
            System.out.println(element);
        }
    }

    private static void luggagePricePageStaff() {
        int keys;
        do {
            System.out.println("Menu Luggage");
            System.out.println("1.Show Luggage");
            System.out.println("2.new Luggage");
            System.out.println("3.edit Luggage");
            System.out.println("4.Delete Luggage");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    showLuggagePage();
                    break;
                case 2:
                    newLuggagePricePage();
                    break;
                case 3:
                    editLuggaePricePage();
                    break;
                case 4:
                    deleteLuggagePricePage();
                    break;
                case 0:
                    keys = 0;
                    break;
                default:
                    break;
            }
        } while (keys != 0);
    }

    private static void deleteLuggagePricePage() {
        showMessengerEnterInformation("ariline name");
        String arilineName = input.nextLine();
        List<String> arilineNameList = flightService.getArilineNameAll();
        boolean isResult = false;
        for (String element : arilineNameList) {
            if (element.equals(arilineName)) {
                isResult = true;
                break;
            }
        }
        if (isResult == true) {
            String luggagePriceId = isCheckNumberRegex("luggage id");

            boolean result = luggagePService.delete(arilineName, luggagePriceId);
            if (result) {
                System.out.println("Delete succes");
            } else {
                showMessengerError("error");
            }
        } else {
            showMessengerError("error");
        }
    }

    private static void editLuggaePricePage() {
        showMessengerEnterInformation("ariline name");
        String arilineName = input.nextLine();
        List<String> arilineNameList = flightService.getArilineNameAll();
        boolean isResult = false;
        for (String element : arilineNameList) {
            if (element.equals(arilineName)) {
                isResult = true;
                break;
            }
        }
        if (isResult == true) {
            String luggagePriceId = isCheckNumberRegex("luggage id");
            showMessengerEnterInformation("name");
            String name = input.nextLine().trim();
            String valume = isCheckNumberRegex("valume");
            String price = isCheckNumberRegex("price");
            boolean result = luggagePService.edit(arilineName, name, valume, price, luggagePriceId);
            if (result) {
                System.out.println("Edit succes");
            } else {
                showMessengerError("error");
            }
        } else {
            showMessengerError("error");
        }
    }

    private static void showLuggagePage() {
        List<String> arilineNameList = flightService.getArilineNameAll();
        for (String elment : arilineNameList) {
            System.out.println("list " + elment);
            List<LuggagePrice> luggagePriceList = luggagePService.getAll(elment);
            for (LuggagePrice e : luggagePriceList) {
                System.out.println(e);
            }
        }
    }

    private static void flightDetailsPageStaff() {
        int keys;
        do {
            System.out.println("Menu Flight");
            System.out.println("1.Show fLight");
            System.out.println("2.new Flight details");
            System.out.println("3.Show Flight details");
            System.out.println("4.edit Flight details");
            System.out.println("5.Delete flight details");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    showFlightPage();
                    break;
                case 2:
                    newflightDetailsPage();
                    break;
                case 3:
                    showMenuFlightDetails();
                    break;
                case 4:
                    editFlightDetailsPageStaff();
                    break;
                case 5:
                    int flightDetailsId = Integer.parseInt(isCheckNumberRegex("flight details id"));
                    boolean isIdExit = flightDetailsService.isIdExit(flightDetailsId);
                    if (!isIdExit) {
                        showMessengerError("Not flight details id");
                        break;
                    }
                    boolean isResult = flightDetailsService.deleteFlightDetails(flightDetailsId);
                    if (isResult){
                        System.out.println("Delete succes");
                    }else {
                        showMessengerError("error");
                    }
                    break;
                case 0:
                    keys = 0;
                    break;
                default:
                    break;
            }
        } while (keys != 0);
    }

    private static void editFlightDetailsPageStaff() {
        int flightDetailsId = Integer.parseInt(isCheckNumberRegex("flight details id"));
        boolean isIdExit = flightDetailsService.isIdExit(flightDetailsId);
        if (!isIdExit) {
            showMessengerError("Not flight details id");
        }
        String date;
        String usedStorageValume;
        int key = -1;
        do {
            date = isCheckDateRegex(DATE_FLIGHT_DETAILS);
            usedStorageValume = isCheckNumberRegex("used storage valume");
            boolean isUsedMax = flightDetailsService.isUsedStorageMax(flightDetailsId, usedStorageValume);
            if (isUsedMax) {
                key = 0;
            } else {
                showMessengerError("used storage valume > storage valume ariline");
            }
        } while (key != 0);
        boolean isResult = flightDetailsService.editFlightDetails(flightDetailsId
                , date, Long.parseLong(usedStorageValume));
        if (isResult) {
            System.out.println("Edit succes");
        } else {
            showMessengerError("Edit erorr");
        }
    }



    private static void showHistoryTicketUser() {
        List<Ticket> ticket = ticketService.getTicketUser(acountSession.getId());
        if (ticket.size()==0){
            System.out.println("No history ticket");
            return;
        }
        showTicket(ticket);
//         keys;
//        do {
        System.out.println("Do you want to see ticket details?");
        System.out.println("1. Yes");
        System.out.println("0. No");
        int keys = Integer.parseInt(isCheckNumberRegex("1/0"));
        if (keys==1){
            int ticketId = Integer.parseInt(isCheckNumberRegex("ticket id"));
            TiketDetails tiketDetails = ticketDetailService.getTicketDetailsUser(ticketId);
            System.out.println(tiketDetails);
        }

//            switch (keys) {
//                case 1:
//
//                    keys=0;
//                    break;
//                default:
//                    keys=0;
//                    break;
//            }
//        } while (keys != 0);
    }

    private static void acountPageAdmin() {
        int keys;
        do {
            System.out.println("Menu account");
            System.out.println("1.Show account");
            System.out.println("2.Register account");
            System.out.println("3.Delete account");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    showAcountPageAdmin();
                    break;
                case 2:
                    newRegisterPage(TYPE_STAFF);
                    break;
                case 3:
                    String id = isCheckNumberRegex("acount id");
                    boolean isResult = acountService.deleteAcountId(Integer.parseInt(id));
                    if (isResult) {
                        System.out.println("delete succes");
                    } else {
                        showMessengerError("error");
                    }
                    break;
                case 0:
                    keys = 0;
                    break;
                default:
                    break;
            }
        } while (keys != 0);
    }

    private static void showAcountPageAdmin() {
        List<Acount> acountList = acountService.getAllAcount();
        if (acountList.size()<=1){
            System.out.println("No acount list");
            return;
        }
        for (Acount element : acountList) {
            if (element.getType().equals(TYPE_ADMIN)){
                continue;
            }
            System.out.println(element);
        }
    }

    private static void chairPageAdmin() {
        int keys;
        do {
            System.out.println("Menu Chair");
            System.out.println("1.Show Chair");
            System.out.println("2.Edit Chair");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    showChairPageAdmin();
                    break;
                case 2:
                    editChairPageAdmin();
                    break;
                case 0:
                    keys = 0;
                    break;
                default:
                    break;
            }
        } while (keys != 0);
    }

    private static void editChairPageAdmin() {
        String chairId = isCheckNumberRegex("chair id");
        boolean isIdExit = chairService.isExitId(Integer.parseInt(chairId));
        if (!isIdExit) {
            showMessengerError("Not chair id");
        }
        Chair chair = chairService.getChairToFlightId(Integer.parseInt(chairId));
        String lineQuantity = isCheckNumberRegex("line quantity");
        boolean isResult = chairService.editChair(chair, lineQuantity);
        chairDetailsService.deleteFile(chair.getIdFlight());
        if (isResult) {
            System.out.println("Edit succes");
            Chair chairResult = chairService.getChairToFlightId(Integer.parseInt(chairId));
            createChairDetailsList(chairResult);
        } else {
            showMessengerError("Edit erorr");
        }
    }

    private static void showChairPageAdmin() {
        List<Chair> chairList = chairService.getAll();
        if (chairList.size()==0){
            System.out.println("No chair list");
            return;
        }
        for (Chair element : chairList) {
            System.out.println(element);
        }
    }

    private static void storagePageAdmin() {
        int keys;
        do {
            System.out.println("Menu Storage");
            System.out.println("1.Show Storage");
            System.out.println("2.Edit Storage");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    showStoragePageAdmin();
                    break;
                case 2:
                    editStoragePageAdmin();
                    break;
                case 0:
                    keys = 0;
                    break;
                default:
                    break;
            }
        } while (keys != 0);
    }

    private static void editStoragePageAdmin() {
        String storageId = isCheckNumberRegex("storage id");
        boolean isIdExit = storageService.isExitId(Integer.parseInt(storageId));
        if (!isIdExit) {
            showMessengerError("Not storage id");
        }
        String valume = isCheckNumberRegex(STORAGE_VALUME);
        boolean isResult = storageService.editStorage(Integer.parseInt(storageId), valume);
        if (isResult) {
            System.out.println("Edit susces");
        } else {
            showMessengerError("Edit error");
        }
    }

    private static void showStoragePageAdmin() {
        List<Storage> storageList = storageService.getAll();
        if (storageList.size()==0){
            System.out.println("No storage list");
            return;
        }
        for (Storage element : storageList) {
            System.out.println(element);
        }
    }

    private static void flightPageAdmin() {
        int keys;
        do {
            System.out.println("Menu Flight");
            System.out.println("1.New Flight");
            System.out.println("2.Show Flight");
            System.out.println("3.Edit Flight");
            System.out.println("4.Delete Flight");
            System.out.println("0.Exit");
            keys = input.nextInt();
            input.nextLine();
            switch (keys) {
                case 1:
                    newFlightPage();
                    break;
                case 2:
                    showFlightPage();
                    break;
                case 3:
                    editFlightPage();
                    break;
                case 4:
                    DeleteFlightPage();
                    break;
                case 0:
                    keys = 0;
                    break;
                default:
                    break;
            }
        } while (keys != 0);
    }

    private static void DeleteFlightPage() {
        String flightId = isCheckNumberRegex("flight id");
        Flight flight = flightService.getFlightToId(Integer.parseInt(flightId));
        if (flight == null) {
            showMessengerError("Not flight id");
            return;
        }
        boolean isResult = flightService.deletFlight(Integer.parseInt(flightId));
        if (isResult) {
            if (storageService.deleteStorage(Integer.parseInt(flightId))) {
                chairService.deleteChair(Integer.parseInt(flightId));
                chairDetailsService.deleteFile(Integer.parseInt(flightId));
                System.out.println("Delete succes");
            }
        } else {
            showMessengerError("error");
        }
    }

    private static void editFlightPage() {
        String flightId = isCheckNumberRegex("flight id");
        Flight flight = flightService.getFlightToId(Integer.parseInt(flightId));
        if (flight == null) {
            showMessengerError("Not flight id");
            return;
        }
        Flight flightNew = getInputFlight();
        flightNew.setId(flight.getId());
        boolean isResult = flightService.editFlight(flightNew);
        if (isResult) {
            System.out.println("Edit succes");
        } else {
            showMessengerError("error");
        }
    }


    private static void logout() {
        acountSession = null;
        System.out.println("logout succes");
    }

    private static void newLoginPage() {
        int key = -1;
        do {
            System.out.println("Login");
            String email = isCheckEmailRegex("email");
            showMessengerEnterInformation("password");
            String passworld = input.nextLine();
            boolean isResult = acountService.checkAcount(email, passworld);
            if (isResult) {
                acountSession = acountService.getUserNow(email, passworld);
                key = 0;
                System.out.println("login sussec");
            } else {
                showMessengerError("wrong email password");
            }
        } while (key != 0);
    }

    private static void newRegisterPage(String type) {
        System.out.println("Register");
        String email = getEmail();
        String fullName = isCheckNameRegex("full name", ConstRegex.FULL_NAME_REGEX);
        String password = getPassword();
        Acount acount = new AcountBuilder()
                .withId(acountService.getAcountId())
                .withEmail(email)
                .withFullName(fullName)
                .withPassword(password)
                .withType(type)
                .builder();
        boolean isResult = acountService.saveAcount(acount);
        if (isResult) {
            IdDefaultHandle.writeIdDefault(acountService.getAcountId() + 1, PATH_ACOUNT_ID);
            System.out.println("succes");
        }
    }

    private static String getPassword() {
        String password = null;
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
        int key = -1;
        String email;
        do {
            email = isCheckEmailRegex("email");
            boolean isEmailExist = acountService.checkEmail(email);
            if (isEmailExist == false) {
                key = 0;
            } else {
                showMessengerError("Already exist");
            }
        } while (key != 0);
        return email;
    }

    private static void viewBookTicket() {

        Map<Long, FlightDetails> map = searchFlightDetails();
        showFlightAll(map);
        if (map.size()==0){
            System.out.println("Not flight details list");
            return;
        }
        showMessengerEnterInformation("code");
        String flightDetailsId = input.nextLine().trim();
        List<ChairPrice> tempChairPriceList = chairPriceService.getAllByFlightDetailId(Integer.parseInt(flightDetailsId));
        showTempChairPriceList(tempChairPriceList);
        showMessengerEnterInformation("code chair");
        String chairPriceId = input.nextLine().trim();
        if (acountSession == null) {
            newLoginPage();
            return;
        }
        ChairPrice chairPrice = tempChairPriceList.get(Integer.parseInt(chairPriceId) - 1);

        String email = isCheckEmailRegex("email");

        String title = getTitle("title", LIST_TITLE);
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
                    if(flightDetailsService.newUsedStorageValume(flightDetailsId,valume)){
                        System.out.println("succes");
                        IdDefaultHandle.writeIdDefault(ticketService.getTicketId() + 1, PATH_TICKET_ID);
                    }
                }
            }
        }else {
            showMessengerError("error");
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
            title = isCheckNumberRegex("key");
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
                .withTicketId(ticketService.getTicketId())
                .withEmail(email)
                .withTitle(title)
                .withLastName(lastName)
                .withFirtName(firtName)
                .withDateOfBirth(dateOfBirth)
                .withNationality(nationality)
                .withPayment(payment)
                .builder();
        boolean isSucces = ticketDetailService.saveTicketDetail(tiketDetails);
        return isSucces;
    }

    private static boolean newTicketPage(String title, String lastName, String firtName, int flightId, long valume) {
        Flight flight = flightService.getFlightToId(flightId);
        Ticket ticket = new TicketBuilder()
                .withId(ticketService.getTicketId())
                .withUserId(acountSession.getId())
                .withTitle(title)
                .withFullName(firtName + lastName)
                .withValume(valume)
                .withAirlineCode(flight.getAirline_name() + "-" + flight.getAirline_id())
                .withDepartureTime(flight.getDeparture_time())
                .withAirlineTime(flight.getArrival_time())
                .withStatus(Boolean.parseBoolean("false"))
                .builder();
        boolean isMessenger = ticketService.saveTicket(ticket);
        return isMessenger;
    }

    private static String getNationality() {
        String nationality = getTitle("notionality", LIST_NOTIONNALITY);
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

    private static Map<Long, FlightDetails> searchFlightDetails() {
        Map<Long, FlightDetails> map = new TreeMap<>();
        List<FlightDetails> dateList=null;
        int key = -1;
        do {
            showMessengerEnterInformation(FROM_LOCATION);
            String from = input.nextLine().trim();
            showMessengerEnterInformation(TO_LOCATION);
            String to = input.nextLine().trim();
            String date = isCheckDateInputRegex(DATE_FLIGHT_DETAILS);
            Search searchFrom = SearchFactory.getSearchFatory(TYPE_FORM);
            Search searchTo = SearchFactory.getSearchFatory(TYPE_TO);
            Search searchDate = SearchFactory.getSearchFatory(TYPE_DATE);
            List<FlightDetails> flightDetailsList = flightDetailsService.getAllList();
            if (flightDetailsList.size()==0){
                return map;
            }
            List<FlightDetails> fromList = searchFrom.searchList(flightDetailsList, from);
            List<FlightDetails> toList = searchTo.searchList(fromList, to);
            dateList = searchDate.searchList(toList, date);
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
                .withId(luggagePService.getLuggagePriceId(airlineName))
                .withName(name)
                .withValume(Long.parseLong(valume))
                .withPrice(Long.parseLong(price))
                .builder();
        boolean isResult = luggagePService.saveLuggagePrice(luggagePrice, airlineName);
        if (isResult) {
            IdDefaultHandle.writeIdDefault(
                    luggagePService.getLuggagePriceId(airlineName) + 1,
                    PATH_LUGGAGE_ID
                            + airlineName
                            + CSV);
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
        showMessengerEnterInformation(FLIGHT);
        showFlightPage();
        showMessengerEnterInformation(ID_FLIGHT);
        String idFlight = input.nextLine().trim();
        String date = isCheckDateRegex(DATE_FLIGHT_DETAILS);
        long storageValume = storageService.getValumeByIdFlight(Integer.parseInt(idFlight));
        FlightDetails flightDetails = new FlightDetailsBuilder()
                .withId(flightDetailsService.getFlightDetailsId())
                .withIdFlight(Integer.parseInt(idFlight))
                .withDate(date)
                .withStorageValume(storageValume)
                .withUsedStorageValume(Long.parseLong("0"))
                .builder();
        boolean isResult = flightDetailsService.saveFlightDetails(flightDetails);
        if (isResult) {
            int idFlightDetails = flightDetailsService.getFlightDetailsId();
            newChairPricePage(
                    LIST_CHAIR_PRICE_ID[0],
                    TYPE_SKY_BOSS,
                    idFlightDetails);
            newChairPricePage(
                    LIST_CHAIR_PRICE_ID[1],
                    TYPE_DELUXE,
                    idFlightDetails);
            newChairPricePage(
                    LIST_CHAIR_PRICE_ID[2],
                    TYPE_ORIGINAL,
                    idFlightDetails);
            IdDefaultHandle.writeIdDefault(idFlightDetails + 1, PATH_FLIGHT_DETAILS_ID);
            renderSeatSpecs(Integer.parseInt(idFlight), idFlightDetails);
        }
    }

    private static void newChairPricePage(int chairPriceId, String type, int idFlightDetail) {
        String price = isCheckNumberRegex(PRICE + type);
        ChairPrice chairPrice = new ChairPriceBuilder()
                .withId(chairPriceId)
                .withType(type)
                .withPrice(Long.parseLong(price))
                .builder();
        chairPriceService.saveChairPrice(chairPrice, idFlightDetail);
    }

    private static void newStoragePage(int flightId) {
        showMessengerEnterInformation("storage");
        String valume = isCheckNumberRegex(STORAGE_VALUME);
        Storage storage = new StorageBuilder()
                .withId(storageService.getStorageId())
                .withIdFlight(flightId)
                .withValume(Long.parseLong(valume))
                .builder();
        boolean isResult = storageService.saveStorage(storage);
        if (isResult) {
            IdDefaultHandle.writeIdDefault(storageService.getStorageId() + 1, PATH_STORAGE_ID);
        }
    }

    private static void newChairPage(int idFlight) {
        String lineQuantity = isCheckNumberRegex(LINE_QUANTITY);

        Chair chair = new ChairBuilder()
                .withId(chairService.getChairId())
                .withIdFlight(idFlight)
                .withLineQuantity(Integer.parseInt(lineQuantity))
                .builder();
        boolean isResult = chairService.saveChair(chair);
        if (isResult) {
            Chair chairResult = chairService.getChair();
            IdDefaultHandle.writeIdDefault(chairResult.getId() + 1, PATH_CHAIR_ID);
            createChairDetailsList(chairResult);
        }
    }

    private static void createChairDetailsList(Chair chairResult) {
        for (int i = 0; i < chairResult.getLineQuantity(); i++) {
            showMessengerEnterInformation("for the sequence " + LIST_LINE[i]);
            addListChairDetailPage(chairResult, i, TYPE_SKY_BOSS);
            addListChairDetailPage(chairResult, i, TYPE_DELUXE);
            addListChairDetailPage(chairResult, i, TYPE_ORIGINAL);
            chairDetailsService.setIndexDefault();
        }
    }


    private static void addListChairDetailPage(Chair chairResult, int i, String type) {
        showMessengerEnterInformation(type);
        int quantityChairSkyBoss = input.nextInt();
        input.nextLine();
        chairDetailsService.saveListChairDetails(
                chairResult,
                LIST_LINE[i],
                quantityChairSkyBoss,
                type);
    }

    private static void showMessengerEnterInformation(String mesenger) {
        System.out.println("Enter information " + mesenger);
    }

    private static void showMessengerError(String mesenger) {
        System.err.println(mesenger);
    }

    private static void newFlightPage() {
        showMessengerEnterInformation("for the sequence new Flight");
        Flight flight = getInputFlight();
        boolean result = flightService.saveFlight(flight);
        if (result) {
            newStoragePage(flightService.getFlightId());
            newChairPage(flightService.getFlightId());
            IdDefaultHandle.writeIdDefault(flightService.getFlightId() + 1, PATH_FLIGHT_ID);
            System.out.println("new succes");
        }
    }

    private static Flight getInputFlight() {
        showMessengerEnterInformation(FROM_LOCATION);
        String fromLocation = input.nextLine().trim();
        showMessengerEnterInformation(TO_LOCATION);
        String toLocation = input.nextLine().trim();
        showMessengerEnterInformation(AIRLINE_ID);
        String airlineId = input.nextLine().trim();
        showMessengerEnterInformation(AIRLINE_NAME);
        String airlineName = input.nextLine().trim();
        String departureTime = isCheckTimerRegex(DEPARTURE_TIME);
        String arrivalTime = isCheckTimerRegex(ARRIVAL_TIME);
        Flight flight = new FlightBuilder()
                .withId(flightService.getFlightId())
                .withFrom_location(fromLocation)
                .withTo_location(toLocation)
                .withAirline_id(Integer.parseInt(airlineId))
                .withAirline_name(airlineName)
                .withDeparture_time(departureTime)
                .withArrival_time(arrivalTime)
                .builder();
        return flight;
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

    private static String isCheckDateInputRegex(String messenger) {
        int key = -1;
        String date;
        do {
            showMessengerEnterInformation(messenger);
            date = input.nextLine().trim();
            if (CheckDateInput.isDate(date)) {
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
        if (list.size()==0){
            System.out.println("Not flight list");
        return;
        }
        for (Flight elment : list) {
            System.out.println(elment);
        }
    }
}