package regex;

import constType.ConstRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckTimer {
    private static boolean isTimeRegex(String validate) {
        Pattern pattern = Pattern.compile(ConstRegex.TIME_REGEX);
        Matcher matcher = pattern.matcher(validate);
        return matcher.matches();
    }

    public static boolean isTime(String validate) {
        if (validate.length() == 0) {
            return false;
        }
        if (!isTimeRegex(validate)) {
            return false;
        }
        String[] cross = validate.split("-");
        String[] twoDots = validate.split(":");
        int hour;
        int minute;
        if (cross.length > 1) {
            hour = Integer.valueOf(cross[0]);
            minute = Integer.valueOf(cross[1]);
            if (hour > 24 || hour < 0) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }

        } else if (twoDots.length > 1) {
            hour = Integer.valueOf(twoDots[0]);
            minute = Integer.valueOf(twoDots[1]);
            if (hour > 24 || hour < 0) {
                return false;
            }
            if (minute > 60 || minute < 0) {
                return false;
            }
        }
        return true;
    }

    public static String formatTime(String time) {
        String[] cross = time.split("-");
        String[] twoDots = time.split(":");
        int hour;
        int minute;
        if (cross.length > 1) {
            hour = Integer.valueOf(cross[0]);
            minute = Integer.valueOf(cross[1]);
            if (hour < 10) {
                if (cross[0].length() <= 1) {
                    cross[0] = "0" + cross[0];
                }
            }
            if (minute < 10) {
                if (cross[1].length() <= 1) {
                    cross[1] = "0" + cross[1];
                }
            }
            return (new StringBuilder().append(cross[0]).append(":").append(cross[1]).append(":00")).toString();
        } else if (twoDots.length > 1) {
            hour = Integer.valueOf(twoDots[0]);
            minute = Integer.valueOf(twoDots[1]);
            if (hour < 10) {
                if (twoDots[0].length() <= 1) {
                    twoDots[0] = "0" + twoDots[0];
                }
            }
            if (minute < 10) {
                if (twoDots[1].length() <= 1) {
                    twoDots[1] = "0" + twoDots[1];
                }
            }
            return (new StringBuilder().append(twoDots[0]).append(":").append(twoDots[1]).append(":00")).toString();
        } else {
            return time+":00";
        }
    }
}
