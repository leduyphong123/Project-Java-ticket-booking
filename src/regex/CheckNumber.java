package regex;

import constType.ConstRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckNumber {
    private static boolean isNumberRegex(String validate){
        Pattern pattern = Pattern.compile(ConstRegex.NUMBER_REGEX);
        Matcher matcher = pattern.matcher(validate);
        return matcher.matches();
    }
    public static boolean isNumber(String validate){
        if (validate.length()==0){
            return false;
        }
        if (!isNumberRegex(validate)){
            return false;
        }
        return true;
    }
}
