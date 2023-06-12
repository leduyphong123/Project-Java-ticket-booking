package regex;

import constType.ConstRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckTimer {
    private static boolean isTimeRegex(String validate){
        Pattern pattern = Pattern.compile(ConstRegex.TIME_REGEX);
        Matcher matcher = pattern.matcher(validate);
        return matcher.matches();
    }
    public static boolean isTime(String validate){
        if (validate.length()==0){
            return false;
        }
        if (!isTimeRegex(validate)){
            return false;
        }
        String[] cross=validate.split("-");
        String[] twoDots=validate.split(":");
        if (cross.length>1){
            if (Integer.valueOf(cross[0])>24 || Integer.valueOf(cross[0])<0){
                return false;
            }
            if (Integer.valueOf(cross[1])>60 || Integer.valueOf(cross[1])<0){
                return false;
            }
        }else if (twoDots.length>1){
            if (Integer.valueOf(twoDots[0])>24 || Integer.valueOf(twoDots[0])<0){
                return false;
            }
            if (Integer.valueOf(twoDots[1])>60 || Integer.valueOf(twoDots[1])<0){
                return false;
            }
        }
        return true;
    }
}
