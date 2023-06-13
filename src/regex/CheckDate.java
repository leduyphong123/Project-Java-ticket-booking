package regex;

import constType.ConstRegex;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckDate {
    private static boolean isDateRegex(String validate) {
        Pattern pattern = Pattern.compile(ConstRegex.DATE_REGEX);
        Matcher matcher = pattern.matcher(validate);
        return matcher.matches();
    }

    public static boolean isDate(String validate) {

        if (validate.length() == 0) {
            return false;
        }
        if (!isDateRegex(validate)) {
            return false;
        }
        String[] cross = validate.split("-");
        String[] slash = validate.split("/");
        int day = 0;
        int month = 0;
        int year = 0;
        if (cross.length > 1) {
            day = Integer.valueOf(cross[0]);
            month = Integer.valueOf(cross[1]);
            year = Integer.valueOf(cross[2]);

        } else if (slash.length > 1) {
            day = Integer.valueOf(slash[0]);
            month = Integer.valueOf(slash[1]);
            year = Integer.valueOf(slash[2]);
        }
        int resultDay = getDateConformYear(month, year);
        if (!isDateNow(day, month, year)) {
            return false;
        }
        if (resultDay == 0) {
            return false;
        }
        if (day > resultDay) {
            return false;
        }
        return true;
    }

    private static boolean isDateNow(int day, int month, int year) {
        LocalDate myObj = LocalDate.now();
        String[] dateNow = myObj.toString().split("-");
        int dayNow = Integer.valueOf(dateNow[2]);
        int monthNow = Integer.valueOf(dateNow[1]);
        int yearNow = Integer.valueOf(dateNow[0]);
        if (year < yearNow) {
            return false;
        }
        if (month < monthNow) {
            return false;
        }
        if (day < dayNow) {
            return false;
        }
        return true;
    }

    public static String formatDate(String date) {
        String[] cross = date.split("-");
        String[] slash = date.split("/");
        int day = 0;
        int month = 0;
        int year = 0;
        if (cross.length > 1) {
            day = Integer.valueOf(cross[0]);
            month = Integer.valueOf(cross[1]);
            year = Integer.valueOf(cross[2]);
            if (day < 10) {
                if (cross[0].length() <= 1) {
                    cross[0] = "0" + cross[0];
                }
            }
            if (month < 10) {
                if (cross[0].length() <= 1) {
                    cross[1] = "0" + cross[1];
                }
            }
            return (new StringBuilder().append(cross[0]).append("-").append(cross[1]).append("-").append(cross[2])).toString();
        } else if (slash.length > 1) {
            day = Integer.valueOf(slash[0]);
            month = Integer.valueOf(slash[1]);
            year = Integer.valueOf(slash[2]);
            if (day < 10) {
                if (slash[0].length() <= 1) {
                    slash[0] = "0" + slash[0];
                }
            }
            if (month < 10) {
                if (slash[1].length() <= 1) {
                    slash[1] = "0" + slash[1];
                }
            }
            return (new StringBuilder().append(slash[0]).append("/").append(slash[1]).append("/").append(slash[2])).toString();
        } else {
            return date;
        }

    }

    private static int getDateConformYear(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (leapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
        }
        return 0;
    }

    private static boolean leapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
