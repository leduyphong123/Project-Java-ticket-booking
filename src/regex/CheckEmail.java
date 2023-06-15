package regex;

import constType.ConstRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmail {
    private static boolean isEmailRegex(String validate){
        Pattern pattern = Pattern.compile(ConstRegex.EMAIL_REGEX);
        Matcher matcher = pattern.matcher(validate);
        return matcher.matches();
    }
    public static boolean isEmail(String validate){
        if (validate.length()==0){
            return false;
        }
        if (!isEmailRegex(validate)){
            return false;
        }
        return true;
    }
}
