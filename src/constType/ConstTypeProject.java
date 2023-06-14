package constType;

import java.lang.reflect.Type;

public class ConstTypeProject {
    public static final String PATH_DEFAULT = "data//";
    public static final String PATH_CHAIR_PAKE_DETAILS_DEFAULT = "listChairDetail//";
    public static final String PATH_SEACT_SPECS_PAKE_DEFAULT = "listSeactSpecs//";
    public static final String CSV = ".csv";
    public static final String PATH_CHAIR_PAKE_PRICE_DEFAULT = "listChairPrice//";
    public static final String PATH_FLIGHT_ID = PATH_DEFAULT + "flightId"+CSV;
    public static final String PATH_FLIGHT_DEFAULT = PATH_DEFAULT + "flight"+CSV;
    public static final String PATH_FLIGHT_DETAILS_DEFAULT = PATH_DEFAULT + "flightDetails"+CSV;
    public static final String PATH_FLIGHT_DETAILS_ID = PATH_DEFAULT + "flightDetailsId"+CSV;
    public static final String PATH_CHAIR_DEFAULT = PATH_DEFAULT + "chair"+CSV;
    public static final String PATH_CHAIR_DETAILS_DEFAULT = PATH_DEFAULT + PATH_CHAIR_PAKE_DETAILS_DEFAULT + "chairDetails_";
    public static final String PATH_CHAIR_PRICE_DEFAULT = PATH_DEFAULT + PATH_CHAIR_PAKE_PRICE_DEFAULT + "chairPrice_";
    public static final String PATH_CHAIR_ID_DEFAULT = PATH_DEFAULT + "chairId"+CSV;
    public static final String PATH_STORAGE_DEFAULT = PATH_DEFAULT + "storage"+CSV;
    public static final String PATH_STORAGE_ID_DEFAULT = PATH_DEFAULT + "storageId"+CSV;
    public static final String PATH_SEAT_SPECS= PATH_DEFAULT+ PATH_SEACT_SPECS_PAKE_DEFAULT + "seatSpecs_";
    public static final String TYPE_ORIGINAL = "original";
    public static final String TYPE_DELUXE = "deluxe";
    public static final String TYPE_SKY_BOSS = "skyBoss";
    public static final String PRICE = "price ";

    public static final String[] LIST_LINE_DEFAULT = {"A", "B", "C", "D", "E", "F", "G", "K", "H"};
    public static final int[] LIST_CHAIR_PRICE_ID_DEFAULT={1,2,3};
}
