package constType;

public class ConstRegex {
    public static final String TIME_REGEX="\\d{1,2}-\\d{1,2}|\\d{1,2}:\\d{1,2}";
    public static final String DATE_REGEX="\\d{1,2}-\\d{1,2}-\\d{4}|\\d{1,2}/\\d{1,2}/\\d{4}";
    public static final String NUMBER_REGEX = "\\d{1,}";
    public static final String FIRT_NAME_REGEX = "[A-Z][a-zA-Z]*";
    public static final String LAST_NAME_REGEX = "[A-z]+([ '-][a-zA-Z]+)*";
    public static final String FULL_NAME_REGEX = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
}
